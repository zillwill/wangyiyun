package example.musicdemo2.fragement;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;

import android.view.ContextMenu.ContextMenuInfo;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import example.musicdemo2.HomeActivity;
import example.musicdemo2.MyMusicList.MyListHelper;
import example.musicdemo2.R;
import example.musicdemo2.adpter.Adapter_CreateMusiclist;
import example.musicdemo2.bean.CreatMusiclist;
import example.musicdemo2.login.User;
import example.musicdemo2.util.CreatMusiclistUtil;

public class Fragment_Music extends Fragment implements View.OnClickListener {
    private LinearLayout ly_localMusic;
    private TextView tv_localeMusicCount;
    private HomeActivity homeActivity;
    private ListView lv_createmusiclist;
    private ImageView iv_add;
    private SQLiteDatabase db;
    private MyListHelper myListHelper;
    public List<CreatMusiclist> cmlist;
    private CreatMusiclistUtil creatMusiclistUtil;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_music, container, false);

        ly_localMusic = view.findViewById(R.id.ly_localMusic);
        tv_localeMusicCount=view.findViewById(R.id.tv_localeMusicCount);
        lv_createmusiclist=view.findViewById(R.id.lv_createmusiclist);
        iv_add=view.findViewById(R.id.add);
        homeActivity= (HomeActivity) getActivity();
        int localMusicCount=homeActivity.getLocaleMusicCount();
        tv_localeMusicCount.setText("("+localMusicCount+")");
        ly_localMusic.setOnClickListener(this);
        iv_add.setOnClickListener(this);

        myListHelper=new MyListHelper(getActivity(),"db_list",null,2);
        db=myListHelper.getReadableDatabase();
        cmlist=myListHelper.getAllData();
        registerForContextMenu(lv_createmusiclist);
        lv_createmusiclist.setAdapter(new Adapter_CreateMusiclist(homeActivity,cmlist));



        return view;

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ly_localMusic:

                homeActivity.jumpMusicListActivity();
                break;
            case R.id.add:
                homeActivity.jumpAddActivity();
                break;
            default:
                break;
        }
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenuInfo menuInfo) {
        // 加载xml中的上下文菜单
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater menuInflater = homeActivity.getMenuInflater();
        menuInflater.inflate(R.menu.main, menu);

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo=(AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        String str=((TextView)menuInfo.targetView.findViewById(R.id.tv_mymusiclistname)).getText().toString();
        switch (item.getItemId()) {
            case R.id.edit:
                Toast.makeText(homeActivity, str, Toast.LENGTH_SHORT).show();
                break;
            case R.id.share:
                Toast.makeText(homeActivity, "分享操作", Toast.LENGTH_SHORT).show();
                break;
            case R.id.delete:
                Toast.makeText(homeActivity, "删除操作", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return super.onContextItemSelected(item);
    }

}