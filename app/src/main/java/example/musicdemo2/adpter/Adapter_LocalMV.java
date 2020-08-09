package example.musicdemo2.adpter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import example.musicdemo2.R;
import example.musicdemo2.bean.LocalMV;
import example.musicdemo2.widget.RollTextView;

/**
 * Created by Administrator on 2020/8/3.
 */

public class Adapter_LocalMV extends BaseAdapter {

    private LayoutInflater mlayoutInflater;
    private List<LocalMV> mlist;

    public Adapter_LocalMV(Context context, List<LocalMV> mlist) {
        this.mlist = mlist;
        mlayoutInflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return mlist.size();
    }

    @Override
    public LocalMV getItem(int position) {
        return mlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class mViewHolder{
        ImageView iv_mvpic;
        RollTextView tv_mvname;
        TextView tv_mvsize;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        mViewHolder holder;
        if (convertView == null) {
            convertView = mlayoutInflater.inflate(R.layout.item_localmv, parent, false);
            holder = new mViewHolder();
            holder.tv_mvname = convertView.findViewById(R.id.tv_mvname);
            holder.tv_mvsize = convertView.findViewById(R.id.tv_mvsize);
            holder.iv_mvpic=convertView.findViewById(R.id.imageView7);
            convertView.setTag(holder);
        } else {
            holder = (mViewHolder) convertView.getTag();
        }

        LocalMV videoInfo = getItem(i);
        holder.tv_mvname.setText(videoInfo.getDisplayName());
        holder.tv_mvsize.setText(ConvertDuration(videoInfo.getDuration()));
        holder.iv_mvpic.setImageBitmap(BitmapFactory.decodeFile(videoInfo.getThumbnailData()));

        return convertView;
    }

    public String ConvertDuration(long num){
        long minute=0;
        long sec=0;
        minute=num/60000;
        sec=num%60;
        if(sec<10) {
            return minute + ":0" + sec;
        }else {
            return minute + ":" + sec;
        }
    }
}
