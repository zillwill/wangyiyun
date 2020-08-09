package example.musicdemo2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import example.musicdemo2.login.LoginActivity;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    SharedPreferences sprfMain;
    SharedPreferences.Editor editorMain;
    Button mlogin,min;
    CheckBox mcheckbox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        //在加载布局文件前判断是否登陆过
//        sprfMain= PreferenceManager.getDefaultSharedPreferences(this);
//        editorMain=sprfMain.edit();
//        //.getBoolean("main",false)；当找不到"main"所对应的键值是默认返回false
//        if(sprfMain.getBoolean("main",false)){
//            Intent intent=new Intent(MainActivity.this,HomeActivity.class);
//            startActivity(intent);
//            MainActivity.this.finish();
//        }
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mlogin=findViewById(R.id.btn_login);
        min=findViewById(R.id.btn_in);
        mcheckbox=findViewById(R.id.checkbox);

        mlogin.setOnClickListener(this);
        min.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (mcheckbox.isChecked()) {
            //checkBox.setChecked(true);
            switch (view.getId()) {
                case R.id.btn_login:
                    Intent intent1 = new Intent(this, LoginActivity.class);
                    startActivity(intent1);
//                    editorMain.putBoolean("main",true);
//                    editorMain.commit();
                    finish();
                    break;
                case R.id.btn_in:
                    Intent intent2 = new Intent(this, HomeActivity.class);
                    startActivity(intent2);
                    finish();
                    break;
            }
        }else {
            Toast.makeText(this,"请先勾选同意《用户协议》《隐私政策》", Toast.LENGTH_SHORT).show();
        }
    }
}