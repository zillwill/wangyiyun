package example.musicdemo2.drawer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import example.musicdemo2.HomeActivity;
import example.musicdemo2.MainActivity;
import example.musicdemo2.R;
import example.musicdemo2.login.LoginActivity;

public class AccountManageActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView mIvaccountback;
    private Button mBtnaccount1,mBtnaccount2,mBtnaccount3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_manage);
        initView();
    }
    private void initView(){
        mIvaccountback=findViewById(R.id.iv_account_back);
        mBtnaccount1=findViewById(R.id.btn_account_1);
        mBtnaccount2=findViewById(R.id.btn_account_2);
        mBtnaccount3=findViewById(R.id.btn_account_3);
        mIvaccountback.setOnClickListener(this);
        mBtnaccount1.setOnClickListener(this);
        mBtnaccount2.setOnClickListener(this);
        mBtnaccount3.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.iv_account_back: //返回
                AccountManageActivity.this.finish();
                break;
            case R.id.btn_account_1:
                Intent intent2 = new Intent(this, ChangePswActivity.class);
                startActivity(intent2);
                break;
            case R.id.btn_account_2:
                Intent intent3 = new Intent(this, ChangeAccountActivity.class);
                startActivity(intent3);
                break;
            case R.id.btn_account_3:
                Intent intent4 = new Intent(this, IsExitActivity.class);
                startActivity(intent4);
                break;
        }
    }
}