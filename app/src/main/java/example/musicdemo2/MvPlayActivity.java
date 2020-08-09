package example.musicdemo2;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Toast;
import android.widget.VideoView;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import example.musicdemo2.adpter.Adapter_LocalMV;
import example.musicdemo2.adpter.Adapter_LocalMusic;
import example.musicdemo2.bean.LocalMV;
import example.musicdemo2.util.MVUtil;


public class MvPlayActivity extends AppCompatActivity {
    private List<LocalMV> mList;
    MVUtil mvUtil=new MVUtil();
    public ListView listView;
    public ImageView iv_cover,bt_play,bt_back,bt_full;
    public VideoView videoView;
    private SeekBar mv_seekBar;
    private Uri url1=null;
    private  int pos=0;
    Handler msgHandler;
    Timer timer;
    RelativeLayout relativeLayoutmv_back,relativeLayout_video;
    TimerTask timerTask;
    private boolean fullscreen=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mv_play);
        mList=mvUtil.GetMvData(MvPlayActivity.this);
        videoView=findViewById(R.id.videoView);
        mv_seekBar=findViewById(R.id.mv_seekbar);
        iv_cover=findViewById(R.id.iv_cover);
        listView=findViewById(R.id.lv_mv_list);
        bt_play=findViewById(R.id.btn_play);
        bt_back=findViewById(R.id.imageView6);
        bt_full=findViewById(R.id.imageView9);
        relativeLayoutmv_back=findViewById(R.id.actionbar_mvback);
        relativeLayout_video=findViewById(R.id.relative_video);

        mv_seekBar.setMax(100);
        listView.setAdapter(new Adapter_LocalMV(this,mList));

        msgHandler = new MyHandler();

        mv_seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar)
            {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {
                // 控制视频跳转到目标位置
                if(videoView.isPlaying())
                {
                    int progress = seekBar.getProgress();
                    int position = progress * videoView.getDuration() / 100;
                    videoView.seekTo( position );
                }
            }
        });



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String str=mList.get(i).getData();
                Uri uri=Uri.parse(str);

                bt_play.setImageDrawable( ContextCompat.getDrawable(MvPlayActivity.this,R.drawable.btn_play));
                iv_cover.setVisibility(View.GONE);
                videoView.setVideoURI(uri);
                videoView.start();

            }
        });

        bt_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 此处可以优化 (事先把两个图标先加载好, 不要每次现加载）
                if(videoView.isPlaying())
                {


                    videoView.pause();
                    bt_play.setImageDrawable( ContextCompat.getDrawable(MvPlayActivity.this,R.drawable.btn_pause));
                }
                else
                {
                    videoView.start();
                    bt_play.setImageDrawable( ContextCompat.getDrawable(MvPlayActivity.this,R.drawable.btn_play));
                }

            }
        });

        bt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MvPlayActivity.this.finish();
            }
        });

        bt_full.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(getRequestedOrientation()!= ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)
                {
                    relativeLayoutmv_back.setVisibility(View.GONE);
                    ViewGroup.LayoutParams params=relativeLayout_video.getLayoutParams();
                    params.height =920;
                    relativeLayout_video.setLayoutParams(params);
                    bt_full.setImageResource(R.drawable.small_screen);
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

                }else{
                    relativeLayoutmv_back.setVisibility(View.VISIBLE);
                    ViewGroup.LayoutParams params=relativeLayout_video.getLayoutParams();
                    params.height =600;
                    relativeLayout_video.setLayoutParams(params);
                    bt_full.setImageResource(R.drawable.btn_fullscreen);
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                }
                //onStart();


            }
        });

    }

    private class MyTimerTask extends TimerTask
    {
        @Override
        public void run()
        {
            if(!videoView.isPlaying()) return;

            // 取得当前播放进度
            int duration = videoView.getDuration(); // 单位 毫秒(ms)
            int position = videoView.getCurrentPosition(); // 单位 毫秒(ms)
            // 注意：在工作线程里不能直接更新UI，必须发消息给UI线程，然后在Handler处理

            // ..发消息给UI线程
            Message msg = new Message();
            msg.what = 1; // 消息类型
            msg.arg1 = duration; // 第1个参数
            msg.arg2 = position; // 第2个参数
            msgHandler.sendMessage(msg);
        }
    }

    @Override
    protected void onStart()
    {
        super.onStart();

        if(timer == null)
        {
            // 启动定时器(间隔500ms)
            timerTask = new MyTimerTask();
            timer = new Timer();
            timer.schedule(timerTask, 500, 500);
        }
    }

    @Override
    protected void onStop()
    {
        if(timer != null)
        {
            //本界面隐藏时，要停止定时器
            // （因为本界面已经隐藏了，如果继续刷新界面将毫无意义、白白耗费CPU)
            timer.cancel();
            timer = null;
        }
        super.onStop();
    }

    private class MyHandler extends Handler
    {
        @Override
        public void handleMessage(Message msg)
        {
            if(msg.what == 1)
            {
                // 从消息里取出进度数据，然后更新UI
                int duration = msg.arg1;
                int position = msg.arg2;
                showProgess(duration, position);
            }
        }
    }

    public void showProgess(int duration, int position)
    {
        // 转成百分比
        int percent = position * 100 / duration;
        mv_seekBar.setProgress( percent);
    }

    private int dip2px(Context context, float dipValue)
    {
        Resources r = context.getResources();
        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, dipValue, r.getDisplayMetrics());
    }




}
