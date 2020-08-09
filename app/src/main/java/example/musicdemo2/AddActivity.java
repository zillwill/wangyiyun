package example.musicdemo2;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import example.musicdemo2.fragement.Fragment_Music;

import java.util.List;

import example.musicdemo2.MyMusicList.MyListHelper;
import example.musicdemo2.R;
import example.musicdemo2.bean.CreatMusiclist;
import example.musicdemo2.fragement.Fragment_Music;
import example.musicdemo2.login.DBOpenHelper;
import example.musicdemo2.util.CreatMusiclistUtil;

public class AddActivity extends AppCompatActivity implements View.OnClickListener {
    EditText et_listName;
    TextView myes,mno;
    private MyListHelper myListHelper;
    private SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        et_listName=findViewById(R.id.message);
        myes=findViewById(R.id.negativeButton);
        mno=findViewById(R.id.positiveButton);
        myes.setOnClickListener(this);
        mno.setOnClickListener(this);

        myListHelper=new MyListHelper(this,"db_list",null,2);

    }

    @Override
    public void onClick(View view) {
        String listName = et_listName.getText().toString().trim();
        switch (view.getId()) {
            case R.id.negativeButton:
                myListHelper.add(listName);
                finish();
                break;
            case R.id.positiveButton:
                finish();
                break;
        }

    }
}