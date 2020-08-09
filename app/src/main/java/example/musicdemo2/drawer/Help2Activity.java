package example.musicdemo2.drawer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import example.musicdemo2.R;

public class Help2Activity extends AppCompatActivity implements View.OnClickListener{
    private ImageView mHelp2_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help2);
        mHelp2_back=findViewById(R.id.iv_help2_back);
        mHelp2_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_help2_back:
                Help2Activity.this.finish();
                break;
        }
    }
}