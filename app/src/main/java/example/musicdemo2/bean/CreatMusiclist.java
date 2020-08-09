package example.musicdemo2.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2020/8/5.
 */

public class CreatMusiclist implements Serializable {


    private  int createrid;
    private String listName;

    public CreatMusiclist(int createrid, String listName) {
        this.createrid = createrid;
        this.listName = listName;
    }



//

    public void setCreater(int createrid) {
        this.createrid = createrid;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public int getCreaterid() {
        return createrid;
    }

    public String getListName() {
        return listName;
    }

}
