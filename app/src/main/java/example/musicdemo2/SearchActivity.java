package example.musicdemo2;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import example.musicdemo2.widget.MarqueeLayout;

public class SearchActivity extends AppCompatActivity {

    private ImageView iv_searchback,iv_innersearch;
    private EditText et_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);


        MarqueeLayout marqueeLayout = (MarqueeLayout) findViewById(R.id.mar);
        TextView tv1 = (TextView) findViewById(R.id.tv1);
        TextView tv2 = (TextView) findViewById(R.id.tv2);
        Typeface font1 = Typeface.createFromAsset(getAssets(), "font/font1.ttf");
        marqueeLayout.start();
        tv1.setVisibility(tv1.getTranslationY() == 0 ? View.VISIBLE : View.GONE);
        tv2.setVisibility(tv2.getTranslationY() == 0 ? View.VISIBLE : View.GONE);
        tv1.setTypeface(font1);
        tv2.setTypeface(font1);

        iv_searchback=findViewById(R.id.search_back);
        iv_innersearch=findViewById(R.id.inner_search);
        et_search=findViewById(R.id.et_search);

        iv_searchback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SearchActivity.this.finish();
            }
        });

        iv_innersearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str=et_search.getText().toString();
                if(str.equals("")) {
                    Toast.makeText(SearchActivity.this,"您什么都没有输入哦",Toast.LENGTH_SHORT).show();
                }else {
                    Uri uri = Uri.parse("https://www.tongzan.com/xunge/?name=" + str + "&type=netease");
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                }
            }
        });

    }




}
