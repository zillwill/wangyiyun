package example.musicdemo2.fragement;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import example.musicdemo2.R;


public class Fragment_More extends Fragment {
    //轮播图
    final private int MSG=1;//消息代码
    private ViewFlipper viewFlipper;
    private Animation[] animations=new Animation[2];//定义动画数组，为ViewFlipper指定切换动画
    private Message message;
    private int[]images=new int[]{R.drawable.more_viewpager_1,R.drawable.more_viewpager_2,
            R.drawable.more_viewpager_3,R.drawable.more_viewpager_4,R.drawable.more_viewpager_5,};
//    //九宫格
//    private GridView gridView_classify;
//    private int[] pic_path_classify = { R.drawable.radio_0, R.drawable.radio_0,
//            R.drawable.radio_0, R.drawable.radio_0, R.drawable.radio_0,
//            R.drawable.radio_0 };

    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_more, container, false);
        viewFlipper=view.findViewById(R.id.messsage_viewflipper);//获取ViewFlipper
        animations[0]= AnimationUtils.loadAnimation(getActivity(),R.anim.slide_in_right);//右进
        animations[1]= AnimationUtils.loadAnimation(getActivity(),R.anim.slide_out_left);//左出
        viewFlipper.setInAnimation(animations[0]);//添加进入动画效果
        viewFlipper.setOutAnimation(animations[1]);//添加退出动画效果
        for(int  i=0;i<images.length;i++) {//遍历图片资源
            ImageView imageView=new ImageView(getActivity());//创建ImageView对象
            imageView.setImageResource(images[i]);//将遍历的图片保存在ImageView中
            viewFlipper.addView(imageView);//  //加载图片

//            //九宫格初始化
//            gridView_classify = view.findViewById(R.id.my_gridview);
//            gridView_classify.setAdapter(new Adapter_GridView(getActivity(),pic_path_classify));
//            gridView_classify.setSelector(new ColorDrawable(Color.TRANSPARENT));
        }
        message= Message.obtain();//获得消息对象
        message.what=MSG;//设置消息代码
        mHandler.sendMessage(message);
        return view;
    }
    private Handler mHandler = new Handler(new Handler.Callback() {//创建android.os.Handler对象
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    viewFlipper.showPrevious();//显示下一张
                    break;
            }
            message=mHandler.obtainMessage(MSG);//获取要发送的消息
            mHandler.sendMessageDelayed(message,3000);//延时3秒发送消息
            return false;
        }
    });


}