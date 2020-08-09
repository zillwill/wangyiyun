package example.musicdemo2.util;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore.Audio.Media;



import java.util.ArrayList;
import java.util.List;

import example.musicdemo2.bean.LocalMusic;

/**
 * Created by Administrator on 2020/8/2.
 */

public class MusicUtil {


    public List<LocalMusic> getMusicData(Context context){
        List<LocalMusic> oList=new ArrayList<LocalMusic>();
        ContentResolver resolver=context.getContentResolver();
        Cursor cursor=resolver.query(Media.EXTERNAL_CONTENT_URI,null,null,null, Media.DEFAULT_SORT_ORDER);
        if(cursor!=null){
            while (cursor.moveToNext()){
                LocalMusic music=new LocalMusic();
                //歌曲ID
                long id=cursor.getLong(cursor.getColumnIndex(Media._ID));
                //歌曲名
                String name=cursor.getString(cursor.getColumnIndex(Media.TITLE));
                //演唱者
                String author=cursor.getString(cursor.getColumnIndex(Media.ARTIST));
                //路径
                String path=cursor.getString(cursor.getColumnIndex(Media.DATA));
                //时长
                long duration=cursor.getLong(cursor.getColumnIndex(Media.DURATION));
                //专辑
                String album=cursor.getString(cursor.getColumnIndex(Media.ALBUM));
                //大小
                String size=cursor.getString(cursor.getColumnIndex(Media.SIZE));

                if(author.equals("<unkonw>")){
                    author="未知艺术家";
                }

                if(duration>3000){
                    music.setName(name);
                    music.setAuthor(author);
                    music.setPath(path);
                    music.setDuration(duration);
                    music.setSize(size);
                    oList.add(music);
                }
            }
        }
        return oList;
    }

}
