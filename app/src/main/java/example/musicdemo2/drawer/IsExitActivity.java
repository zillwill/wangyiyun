package example.musicdemo2.drawer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import example.musicdemo2.MainActivity;
import example.musicdemo2.R;

public class IsExitActivity extends AppCompatActivity implements View.OnClickListener{
    private Button mMYes,mMNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_is_exit);
        initView();
    }
    private void initView(){
        mMYes=findViewById(R.id.Myes);
        mMNo=findViewById(R.id.Mno);
        mMYes.setOnClickListener(this);
        mMNo.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.Myes:
                Intent intent1=new Intent(this, MainActivity.class);
                startActivity(intent1);
                finish();
                break;
            case R.id.Mno:
                finish();
                break;
        }
    }
}