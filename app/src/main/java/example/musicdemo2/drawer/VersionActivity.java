package example.musicdemo2.drawer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import example.musicdemo2.R;

public class VersionActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView mback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_version);
        mback=findViewById(R.id.iv_version_back);
        mback.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_version_back:
                finish();
                break;
        }
    }
}