package com.bignerdranch.android.recycle.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.bignerdranch.android.recycle.R;
import com.bignerdranch.android.recycle.activity.MainActivity;

/**
 * Created by asus on 2017/11/10.
 */

public class MyFragment extends Fragment {
    private View view;
    private FragmentManager fm ;
    private Fragment fragment;
    private LinearLayout frame;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        view = inflater.inflate(R.layout.my_fragment,container,false);
        init();
        listener();
        return view;
    }

    public void init() {
        frame = (LinearLayout)view.findViewById(R.id.frame_linear);
    }
    public void listener() {
        frame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = MainActivity.newInstance(getContext(),3);
                startActivity(intent);
            }
        });
    }
}
