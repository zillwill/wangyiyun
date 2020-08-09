package example.musicdemo2.adpter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import example.musicdemo2.R;
import example.musicdemo2.bean.CreatMusiclist;
import example.musicdemo2.bean.LocalMV;

/**
 * Created by Administrator on 2020/8/5.
 */

public class Adapter_CreateMusiclist extends BaseAdapter {
    private LayoutInflater cmlayoutInflater;
    private List<CreatMusiclist> cmlist;

    public Adapter_CreateMusiclist(Context context, List<CreatMusiclist> cmlist) {
        this.cmlist = cmlist;
        cmlayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return cmlist.size();
    }

    @Override
    public CreatMusiclist getItem(int i) {
        return cmlist.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public class cmViewHolder{
        TextView cmListname;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        Adapter_CreateMusiclist.cmViewHolder holder;
        if (convertView == null) {
            convertView = cmlayoutInflater.inflate(R.layout.item_mymusiclist, parent, false);
            holder = new cmViewHolder();
            holder.cmListname = convertView.findViewById(R.id.tv_mymusiclistname);

            convertView.setTag(holder);
        } else {
            holder = (Adapter_CreateMusiclist.cmViewHolder) convertView.getTag();
        }

        CreatMusiclist musiclistInfo = getItem(i);
        holder.cmListname.setText(musiclistInfo.getListName());


        return convertView;
    }
}
