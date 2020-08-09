package example.musicdemo2.drawer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import example.musicdemo2.R;

public class Help1Activity extends AppCompatActivity implements View.OnClickListener{
    private Button mHelp1_1,mHelp1_2,mHelp1_3,mHelp1_4,mHelp1_5;
    private ImageView mHelp1_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help1);
        initView();
    }

    private void initView() {
        mHelp1_1=findViewById(R.id.btn_help1_1);
        mHelp1_2=findViewById(R.id.btn_help1_2);
        mHelp1_3=findViewById(R.id.btn_help1_3);
        mHelp1_4=findViewById(R.id.btn_help1_4);
        mHelp1_5=findViewById(R.id.btn_help1_5);
        mHelp1_back=findViewById(R.id.iv_help1_back);
        mHelp1_1.setOnClickListener(this);
        mHelp1_2.setOnClickListener(this);
        mHelp1_3.setOnClickListener(this);
        mHelp1_4.setOnClickListener(this);
        mHelp1_5.setOnClickListener(this);
        mHelp1_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        LayoutInflater factorys = LayoutInflater.from(Help1Activity.this);
        final View textEntryView = factorys.inflate(R.layout.activity_blank, null);
        TextView tv = (TextView) textEntryView.findViewById(R.id.tv_blank);
        switch (view.getId()){
            case R.id.btn_help1_1:
                Intent intent=new Intent(this,BlankActivity.class);
                startActivity(intent);
                tv.setText("问：怎么注销账号"+"\n\n"+"答：目前注销账号仅支持在PC端操作，请您在电脑网页端登录APP官网");
                break;
            case R.id.iv_help1_back:
                Intent intent1=new Intent(this,HelpActivity.class);
                startActivity(intent1);
                finish();
                break;

        }
    }
}