package example.musicdemo2.drawer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import example.musicdemo2.R;

public class BlankActivity extends AppCompatActivity implements View.OnClickListener {
    TextView mTv;
    ImageView mBlank_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blank);
        mTv=findViewById(R.id.tv_blank);
        mBlank_back=findViewById(R.id.iv_blank_back);
        mBlank_back.setOnClickListener(this);
        mTv.setText("问：怎么注销账号"+"\n\n"+"答：目前注销账号仅支持在PC端操作，请您在电脑网页端登录APP官网");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_blank_back:
                BlankActivity.this.finish();
                break;
        }

    }
}