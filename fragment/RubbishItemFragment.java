package com.bignerdranch.android.recycle.fragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bignerdranch.android.recycle.R;

public class RubbishItemFragment extends Fragment {
    private View view;
    private static final String ARG_LOCATION = "location";
    private TextView location;
    private LinearLayout mReturn;
    private LinearLayout paper;
    private LinearLayout plastic;
    private LinearLayout metal;
    private LinearLayout drygoods;
    private Fragment fragment;
    private FragmentManager fm ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        view = inflater.inflate(R.layout.rubbish_item_fragment,container,false);
        init();
        listener();
        return view;
    }

    public void init() {
        fm = getActivity().getSupportFragmentManager();
        location = (TextView)view.findViewById(R.id.location_textView);
        mReturn = (LinearLayout) view.findViewById(R.id.return_linear);
        paper = (LinearLayout)view.findViewById(R.id.paper_linear);
        plastic = (LinearLayout)view.findViewById(R.id.plate_linear);
        metal = (LinearLayout)view.findViewById(R.id.metal_linear);
        drygoods = (LinearLayout)view.findViewById(R.id.drygoods_linear);
    }
    public void listener() {
        String s = (String) getArguments().getSerializable(ARG_LOCATION);
        location.setText(s);
        mReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        paper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment = new PaperItemFragment();
                fm.beginTransaction().add(R.id.item_container,fragment).addToBackStack(null).commit();
            }
        });
        plastic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment = new PlasticItemFragment();
                fm.beginTransaction().add(R.id.item_container,fragment).addToBackStack(null).commit();
            }
        });
        metal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment = new MetalItemFragment();
                fm.beginTransaction().add(R.id.item_container,fragment).addToBackStack(null).commit();
            }
        });
        drygoods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment = new DrygoodsItemFragment();
                fm.beginTransaction().add(R.id.item_container,fragment).addToBackStack(null).commit();
            }
        });
    }
    public static RubbishItemFragment newInstance(String s){
        Bundle args = new Bundle();
        args.putSerializable(ARG_LOCATION,s);
        RubbishItemFragment fragment = new RubbishItemFragment();
        fragment.setArguments(args);
        return  fragment;
    }
}
