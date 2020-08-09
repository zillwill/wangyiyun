package example.musicdemo2.util;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import example.musicdemo2.MyMusicList.MyListHelper;
import example.musicdemo2.bean.CreatMusiclist;
import example.musicdemo2.fragement.Fragment_Music;
import example.musicdemo2.login.User;

/**
 * Created by Administrator on 2020/8/5.
 */

public class CreatMusiclistUtil {
    public List<CreatMusiclist> cmlist;
    public static final int NO_POSITION = -1;


    private int id;

    private String listName;

    private int numOfSongs;

    public List<CreatMusiclist> getcreatemusiclistdata(){
        cmlist= new ArrayList<CreatMusiclist>();

        cmlist.add(new CreatMusiclist(1,"古风"));
        return cmlist;

    }

}
