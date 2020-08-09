package example.musicdemo2.widget;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;

public class MarqueeLayout extends FrameLayout implements ViewTreeObserver.OnGlobalLayoutListener {
    private View view1;
    private View view2;
    public int interval = 2000;
    public MarqueeLayout(Context context) {
        this(context,null);
    }
    public MarqueeLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }
    public MarqueeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        getViewTreeObserver().addOnGlobalLayoutListener(this);
    }
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if(getChildCount()!=2){
            throw  new IllegalArgumentException("MarqueeLayout should have 2 child!");
        }
        view1 = getChildAt(0);
        view2 = getChildAt(1);
    }
    @Override
    public void onGlobalLayout() {
        getViewTreeObserver().removeGlobalOnLayoutListener(this);

        view2.setTranslationY(view2.getHeight());
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            startAnim();
        }
    };
    boolean isAniming = false;
    /**
     * 开始滚动
     */
    public void start(){
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                getViewTreeObserver().removeGlobalOnLayoutListener(this);
                startAnim();
            }
        });
    }
    public void startAnim(){
        if(isAniming)return;

        isAniming = true;
        int targetY1 = view1.getTranslationY()==0?-view1.getHeight():0;
        int targetY2 = view2.getTranslationY()==0?-view2.getHeight():0;

        ViewCompat.animate(view1)
                .translationY(targetY1)
                .setListener(new ViewPropertyAnimatorListenerAdapter(){
                    @Override
                    public void onAnimationEnd(View view) {
                        super.onAnimationEnd(view);
                        //移动到下方
                        resetView(view);
                    }
                })
                .setDuration(400).start();
        ViewCompat.animate(view2)
                .translationY(targetY2)
                .setListener(new ViewPropertyAnimatorListenerAdapter(){
                    @Override
                    public void onAnimationEnd(View view) {
                        super.onAnimationEnd(view);
                        //移动到下方
                        resetView(view);
                        isAniming = false;
                        handler.sendEmptyMessageDelayed(0,interval);
                    }
                })
                .setDuration(400)
                .start();
    }
    private void resetView(View view) {
        if(view.getTranslationY()==-view.getHeight()){
            view.setTranslationY(view.getHeight()*2);
        }
    }
    public void stop(){
        handler.removeCallbacksAndMessages(null);
    }
}
