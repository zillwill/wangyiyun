package example.musicdemo2.service;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;

import example.musicdemo2.bean.LocalMusic;

public class MusicService extends Service {
    private int newmusic;
    private LocalMusic music;
    private int mstate = 10;//10为播放第一首歌曲 11为暂停 12为继续播放
    private int currPosition, duration;//当前播放时间 总时长
    private MediaPlayer player = new MediaPlayer();//播放歌曲类
    private MusicServiceBroadcastReceiver musicServiceBroadcastReceiver;

    @Override
    public void onCreate() {
        super.onCreate();
        //注册广播
        registerServiceBroadcastReceiver();
        //监听音乐是否播放完
        monitorMusicIsCompletion();

        //监听来电(注：我发现某些手机监听不了，用其他方法吧，比如广播)
        TelephonyManager tmgr = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);// 获取电话服务
        tmgr.listen(mPhoneStateListener, PhoneStateListener.LISTEN_CALL_STATE);
    }

    private boolean mResumeAfterCall = false;
    //监听来电状态，来电暂停播放，挂断继续播放
    private PhoneStateListener mPhoneStateListener = new PhoneStateListener() {
        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
            if (state == TelephonyManager.CALL_STATE_RINGING) {//响铃状态
                Log.e("MusicService" ,"电话响铃");
                AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
                int ringvolume = audioManager.getStreamVolume(AudioManager.STREAM_RING);
                if (ringvolume > 0) {
                    mResumeAfterCall = (player.isPlaying() || mResumeAfterCall);
                    player.pause();
                    mstate = 12;
                }
            } else if (state == TelephonyManager.CALL_STATE_OFFHOOK) {//通话状态
                Log.e("MusicService" ,"通话状态");
                mResumeAfterCall = (player.isPlaying() || mResumeAfterCall);
                player.pause();
                mstate = 12;
            } else if (state == TelephonyManager.CALL_STATE_IDLE) {// 挂机状态
                Log.e("MusicService" ,"挂机状态");
                if (mResumeAfterCall) {
                    player.start();
                    mstate = 11;
                    mResumeAfterCall = false;
                }
            }
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * 注册广播接收器，用于接收activity发的广播
     */
    private void registerServiceBroadcastReceiver() {
        musicServiceBroadcastReceiver = new MusicServiceBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("musicService");
        intentFilter.addAction(AudioManager.ACTION_AUDIO_BECOMING_NOISY);//拔出耳机的广播
        registerReceiver(musicServiceBroadcastReceiver, intentFilter);
    }

    /**
     * 监听歌曲播放是否完成
     */
    public void monitorMusicIsCompletion() {
        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            //当歌曲播放完成后调用该方法
            public void onCompletion(MediaPlayer mp) {
                //发送广播给Activity播放下一曲
                Intent intent = new Intent("musicListActivity");//列表页
                intent.putExtra("playFinish", 1);
                sendBroadcast(intent);

                Intent intent2 = new Intent("musicPlayActivity");//播放页
                intent2.putExtra("playFinish", 1);
                sendBroadcast(intent2);

                Intent intent3 = new Intent("homeActivity");//首页
                intent3.putExtra("playFinish", 1);
                sendBroadcast(intent3);

                currPosition = 0;
                duration = 0;
            }
        });
    }

    class MusicServiceBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            //接收是否播放新歌曲
            newmusic = intent.getIntExtra("newmusic", -1);
            if (newmusic != -1 && newmusic == 1) {
                music = (LocalMusic) intent.getSerializableExtra("music");
                if (music != null) {
                    //播放歌曲
                    playMusic(music);
                    mstate = 11;
                }
            }

            //如果是暂停播放按钮，根据mstate控制播放状态
            int isPlayOrPause = intent.getIntExtra("isPlayOrPause", -1);
            if (isPlayOrPause != -1 && isPlayOrPause == 1) {
                switch (mstate) {
                    //第一次播放歌曲
                    case 10:
                        music = (LocalMusic) intent.getSerializableExtra("music");
                        playMusic(music);
                        mstate = 11;
                        break;
                    //暂停
                    case 11:
                        player.pause();
                        mstate = 12;
                        break;
                    //继续播放
                    case 12:
                        player.start();
                        mstate = 11;
                        break;
                }
            }

            //拖动进度条发送的广播，先获取歌曲进度位置
            int progress = intent.getIntExtra("progress", -1);
            if (progress != -1) {
                //转换为播放歌曲的时间(毫秒)
                currPosition = (int) (((progress * 1.0) / 100) * duration);
                player.seekTo(currPosition);
            }

            //拔出耳机，暂停
            if(intent.getAction().equals(AudioManager.ACTION_AUDIO_BECOMING_NOISY)){
                player.pause();
                mstate = 12;
            }

            //将当前状态发送给Activity更新按钮
            Intent intent2 = new Intent();
            intent2.setAction("musicListActivity");//列表
            intent2.putExtra("state", mstate);
            sendBroadcast(intent2);

            Intent intentMusicPlay = new Intent();
            intentMusicPlay.setAction("musicPlayActivity");//播放页
            intentMusicPlay.putExtra("state", mstate);
            sendBroadcast(intentMusicPlay);

            Intent intentHome = new Intent();
            intentHome.setAction("homeActivity");//首页
            intentHome.putExtra("state", mstate);
            sendBroadcast(intentHome);
        }
    }

    /**
     * 播放歌曲
     *
     * @param localMusic:歌曲对象
     */
    public void playMusic(LocalMusic localMusic) {
        //player不为空，说明正在播放歌曲
        if (player != null) {
            //停止播放
            player.stop();
            //等待
            player.reset();
            try {
                //获取歌曲播放路径
                player.setDataSource(localMusic.getPath());
                //准备歌曲
                player.prepare();
                //播放歌曲
                player.start();

                duration = player.getDuration();//获取当前播放歌曲总时长

                new updateProgressThread().start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    class updateProgressThread extends Thread {
        @Override
        public void run() {
            do {
                try {
                    Thread.sleep(1000);
                    Message msg = new Message();
                    msg.what = 1;
                    mHandler.sendMessage(msg);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } while (currPosition < duration);
        }
    }

    //在主线程里面处理消息并更新UI界面
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    currPosition = player.getCurrentPosition();//获取播放歌曲的当前时间
                    Intent intent = new Intent("musicListActivity");//列表页
                    intent.putExtra("currPosition", currPosition);
                    intent.putExtra("duration", duration);
                    intent.putExtra("state", mstate);
                    sendBroadcast(intent);

                    Intent intent2 = new Intent("musicPlayActivity");//播放页
                    intent2.putExtra("currPosition", currPosition);
                    intent2.putExtra("duration", duration);
                    intent2.putExtra("state", mstate);
                    sendBroadcast(intent2);

                    Intent intent3 = new Intent("homeActivity");//首页
                    intent3.putExtra("currPosition", currPosition);
                    intent3.putExtra("duration", duration);
                    intent3.putExtra("state", mstate);
                    sendBroadcast(intent3);
                    break;
                default:
                    break;

            }
        }
    };

    public void onDestroy() {
        //取消来电监听
        TelephonyManager tmgr = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        tmgr.listen(mPhoneStateListener, 0);
        super.onDestroy();
    }
}
