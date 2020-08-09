package example.musicdemo2.drawer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import example.musicdemo2.R;

public class HelpActivity extends AppCompatActivity implements View.OnClickListener{
    private Button mHelp1,mHelp2,mHelp3,mHelp4,mHelp5,mHelp6;
    private ImageView mHelp_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        initView();
    }

    private void initView() {
        mHelp1=findViewById(R.id.help_1);
        mHelp2=findViewById(R.id.help_2);
        mHelp3=findViewById(R.id.help_3);
        mHelp4=findViewById(R.id.help_4);
        mHelp5=findViewById(R.id.help_5);
        mHelp6=findViewById(R.id.help_6);
        mHelp_back=findViewById(R.id.iv_help_back);
        mHelp1.setOnClickListener(this);
        mHelp2.setOnClickListener(this);
        mHelp3.setOnClickListener(this);
        mHelp4.setOnClickListener(this);
        mHelp5.setOnClickListener(this);
        mHelp6.setOnClickListener(this);
        mHelp_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.help_1:
                Intent intent1=new Intent(this,Help1Activity.class);
                startActivity(intent1);
                break;
            case R.id.help_2:
                Intent intent2=new Intent(this,Help2Activity.class);
                startActivity(intent2);
                break;
            case R.id.iv_help_back:
                HelpActivity.this.finish();
        }
    }
}