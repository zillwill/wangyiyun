package example.musicdemo2.fragement;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import example.musicdemo2.HomeActivity;
import example.musicdemo2.R;


public class Fragment_MV extends Fragment  {
    private LinearLayout linearLayout;
    private HomeActivity homeActivity=new HomeActivity();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_mv,container,false);

        linearLayout=view.findViewById(R.id.ly_localMV);
        homeActivity= (HomeActivity) getActivity();
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homeActivity.jumpMvPlayActivity();
            }
        });
        return view;


    }







}
