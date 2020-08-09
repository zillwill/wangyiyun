package example.musicdemo2.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;



import java.util.ArrayList;

import example.musicdemo2.HomeActivity;
import example.musicdemo2.MainActivity;
import example.musicdemo2.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
//    SharedPreferences sp;
//    SharedPreferences.Editor editor;
    /**
     * 声明自己写的 DBOpenHelper 对象
     * DBOpenHelper(extends SQLiteOpenHelper) 主要用来
     * 创建数据表
     * 然后再进行数据表的增、删、改、查操作
     */
    private DBOpenHelper mDBOpenHelper;
    private ImageView mLvregister;
    private RelativeLayout mRlLoginactivityTop;
    private EditText mEtLoginactivityUsername;
    private EditText mEtLoginactivityPassword;
    private LinearLayout mLlLoginactivityTwo;
    private Button mBtLoginactivityLogin;
    private ImageView mIvLoginactivityBack;
    private CheckBox mRempsw,mAutologin;
    /**
     * 创建 Activity 时先来重写 onCreate() 方法
     * 保存实例状态
     * super.onCreate(savedInstanceState);
     * 设置视图内容的配置文件
     * setContentView(R.layout.activity_login);
     * 上面这行代码真正实现了把视图层 View 也就是 layout 的内容放到 Activity 中进行显示
     * 初始化视图中的控件对象 initView()
     * 实例化 DBOpenHelper，待会进行登录验证的时候要用来进行数据查询
     * mDBOpenHelper = new DBOpenHelper(this);
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        initView();

        mDBOpenHelper = new DBOpenHelper(this);
//        sp = this.getSharedPreferences("userInfo", Context.MODE_WORLD_READABLE);
//        //判断记住密码多选框的状态
//        if(sp.getBoolean("ISCHECK", false))
//        {
//            //设置默认是记录密码状态
//            mRempsw.setChecked(true);
//            mEtLoginactivityUsername.setText(sp.getString("USER_NAME", ""));
//            mEtLoginactivityPassword.setText(sp.getString("PASSWORD", ""));
//            //判断自动登陆多选框状态
//            if(sp.getBoolean("AUTO_ISCHECK", false))
//            {
//                //设置默认是自动登录状态
//                mAutologin.setChecked(true);
//                //跳转界面
//                Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
//                LoginActivity.this.startActivity(intent);
//
//            }
//        }
    }

    /**
     * onCreae()中大的布局已经摆放好了，接下来就该把layout里的东西
     * 声明、实例化对象然后有行为的赋予其行为
     * 这样就可以把视图层View也就是layout 与 控制层 Java 结合起来了
     */
    private void initView(){
        // 初始化控件
        mBtLoginactivityLogin = findViewById(R.id.bt_loginactivity_login);
        mRlLoginactivityTop = findViewById(R.id.rl_loginactivity_top);
        mEtLoginactivityUsername = findViewById(R.id.et_loginactivity_username);
        mEtLoginactivityPassword = findViewById(R.id.et_loginactivity_password);
        mLlLoginactivityTwo = findViewById(R.id.ll_loginactivity_two);
        mIvLoginactivityBack=findViewById(R.id.iv_loginactivity_back);
        mLvregister=findViewById(R.id.lv_register);
        mRempsw=(CheckBox) findViewById(R.id.rem_psw);
        mAutologin=(CheckBox) findViewById(R.id.auto_login);

        // 设置点击事件监听器
        mIvLoginactivityBack.setOnClickListener(this);
        mBtLoginactivityLogin.setOnClickListener(this);
        mLvregister.setOnClickListener(this);
//        //监听记住密码多选框按钮事件
//        mRempsw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
//            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
//                if (mRempsw.isChecked()) {
//
//                    System.out.println("记住密码已选中");
//                    sp.edit().putBoolean("ISCHECK", true).commit();
//
//                }else {
//
//                    System.out.println("记住密码没有选中");
//                    sp.edit().putBoolean("ISCHECK", false).commit();
//
//                }
//
//            }
//        });
//
//        //监听自动登录多选框事件
//        mAutologin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
//                if (mAutologin.isChecked()) {
//                    System.out.println("自动登录已选中");
//                    sp.edit().putBoolean("AUTO_ISCHECK", true).commit();
//
//                } else {
//                    System.out.println("自动登录没有选中");
//                    sp.edit().putBoolean("AUTO_ISCHECK", false).commit();
//                }
//            }
//        });

    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.lv_register:
                Intent intent2 = new Intent(this, RegisterActivity.class);
                startActivity(intent2);
                finish();
                break;
            //返回
            case R.id.iv_loginactivity_back:
                Intent intent1 = new Intent(this, MainActivity.class);
                startActivity(intent1);
                finish();
                break;
            //登录验证
            case R.id.bt_loginactivity_login:
                String name = mEtLoginactivityUsername.getText().toString().trim();
                String password = mEtLoginactivityPassword.getText().toString().trim();
                if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(password)) {
                    ArrayList<User> data = mDBOpenHelper.getAllData();
                    boolean match = false;
                    for (int i = 0; i < data.size(); i++) {
                        User user = data.get(i);
                        if (name.equals(user.getName()) && password.equals(user.getPassword())) {
                            match = true;
                            break;
                        } else {
                            match = false;
                        }
                    }
                    if (match) {
                        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
                        //登录成功和记住密码框为选中状态才保存用户信息
//                        if(mRempsw.isChecked())
//                        {
//                            //记住用户名、密码、
//                            editor = sp.edit();
//                            editor.putString("USER_NAME",name);
//                            editor.putString("PASSWORD",password);
//                            editor.commit();
//                        }
                        Intent intent = new Intent(this, HomeActivity.class);
                        startActivity(intent);
                        finish();//销毁此Activity
                    } else {
                        Toast.makeText(this, "用户名或密码不正确，请重新输入", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "请输入你的用户名或密码", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

}