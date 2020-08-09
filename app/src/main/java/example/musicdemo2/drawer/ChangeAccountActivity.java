package example.musicdemo2.drawer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import example.musicdemo2.MainActivity;
import example.musicdemo2.R;
import example.musicdemo2.login.LoginActivity;

public class ChangeAccountActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mYes,mNo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_account);
        initView();
    }

    private void initView(){
        mYes=findViewById(R.id.yes);
        mNo=findViewById(R.id.no);
        mYes.setOnClickListener(this);
        mNo.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.yes:
                Intent intent1=new Intent(this, LoginActivity.class);
                startActivity(intent1);
                finish();
                break;
            case R.id.no:
                finish();
                break;
        }
    }

}