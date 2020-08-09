package example.musicdemo2.MyMusicList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import example.musicdemo2.bean.CreatMusiclist;

public class MyListHelper extends SQLiteOpenHelper {
    private SQLiteDatabase db;

    public MyListHelper(Context context,String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "db_list", null, 2);
        db=getReadableDatabase();
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS mylist(" +
                "list_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "listName TEXT," +
                "musicNumber INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS mylist");
        onCreate(db);
    }

    /**
     * add()
     * delete()
     * update()
     * getAllData()
     */
    public void add(String listName) {
        db.execSQL("INSERT INTO mylist(listName) VALUES(?)", new Object[]{listName});
    }



        /**
         容器
         * @return
         */
    public ArrayList<CreatMusiclist> getAllData(){

        ArrayList<CreatMusiclist> list = new ArrayList<CreatMusiclist>();
        Cursor cursor = db.query("mylist",null,null,null,null,null,null);
        while(cursor.moveToNext()){
            String listName = cursor.getString(cursor.getColumnIndex("listName"));
            list.add(new CreatMusiclist(1,listName));
        }
        return list;
    }
}



