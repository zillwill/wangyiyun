package example.musicdemo2.drawer;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import example.musicdemo2.R;
import example.musicdemo2.login.DBOpenHelper;
import example.musicdemo2.login.User;

public class ChangePswActivity extends AppCompatActivity implements View.OnClickListener{
    Button btn_save,btn_back;
    private DBOpenHelper mDBOpenHelper;
    private EditText mEtusername;
    private EditText mEtoldpassword;
    private EditText mEtnewpassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changepsw);
        btn_save=findViewById(R.id.save);
        btn_save.setOnClickListener(this);

        btn_back=findViewById(R.id.back);
        btn_back.setOnClickListener(this);

        mEtusername = findViewById(R.id.et_loginactivity_username);
        mEtoldpassword = findViewById(R.id.et_oldpassword);
        mEtnewpassword = findViewById(R.id.et_newpassword);
        mDBOpenHelper = new DBOpenHelper(this);
    }
    @Override
    public void onClick(View v) {
        String username = mEtusername.getText().toString().trim();
        String oldpassword = mEtoldpassword.getText().toString().trim();
        String newpassword = mEtnewpassword.getText().toString().trim();
        if (v.getId() == R.id.save) {
            if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(oldpassword)) {
                ArrayList<User> data = mDBOpenHelper.getAllData();
                boolean match = false;
                for (int i = 0; i < data.size(); i++) {
                    User user = data.get(i);
                    if (username.equals(user.getName()) && oldpassword.equals(user.getPassword())) {
                        match = true;
                        break;
                    } else {
                        match = false;
                    }
                }
                if (match) {
                    if (newpassword.equals(oldpassword)) {
                        Toast.makeText(this, "新旧密码一致，请重新输入", Toast.LENGTH_SHORT).show();
                    } else {
                        mDBOpenHelper.update(username, oldpassword, newpassword);
                        Toast.makeText(this, "修改密码成功", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                } else {
                    Toast.makeText(this, "用户名或密码不正确，请重新输入", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "请输入你的用户名或密码", Toast.LENGTH_SHORT).show();
            }


        } else if (v.getId() == R.id.back) {
            finish();
        }
    }
}