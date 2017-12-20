package com.bignerdranch.android.recycle.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.LinearLayout;

import com.bignerdranch.android.recycle.R;
import com.bignerdranch.android.recycle.fragment.ChatFragment;
import com.bignerdranch.android.recycle.fragment.MainFragment;
import com.bignerdranch.android.recycle.fragment.MessageFragment;
import com.bignerdranch.android.recycle.fragment.MyFragment;
import com.bignerdranch.android.recycle.fragment.ScrapFrameFragment;

public class MainActivity extends FragmentActivity {
    private static final String ARG_BOTTOMBAR = "bottombar";
    private FragmentManager fragmentManager=getSupportFragmentManager();
    private Fragment mFragment;
    private LinearLayout main;
    private LinearLayout my;
    private LinearLayout message;
    private LinearLayout bottombar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        listener();
        mFragment = getFragment();
        if (mFragment instanceof ChatFragment||mFragment instanceof ScrapFrameFragment){
            bottombar.setVisibility(View.GONE);
        }
        fragmentManager.beginTransaction().add(R.id.container,mFragment).commit();

    }
    public static Intent newInstance(Context context, int i){
        Intent intent = new Intent(context,MainActivity.class);
        intent.putExtra(ARG_BOTTOMBAR,i);
        return intent;
    }
    public Fragment getFragment(){
        int i = getIntent().getIntExtra(ARG_BOTTOMBAR,0);
        if(i==0){
            return new MainFragment();
        }else if(i==1) {
            Fragment fragment = ChatFragment.newInstance("买主");
            return fragment;
        }else if(i==2) {
            Fragment fragment = ChatFragment.newInstance("卖主");
            return fragment;
        }else if(i==3){
            Fragment fragment = new ScrapFrameFragment();
            return fragment;
        }
        return new MainFragment();
    }
    public void init(){
        my = (LinearLayout)findViewById(R.id.my_linear);
        main = (LinearLayout)findViewById(R.id.main_linear);
        message = (LinearLayout)findViewById(R.id.message_linear);
        bottombar = (LinearLayout)findViewById(R.id.bottombar_linear);

    }
    public void listener(){
        main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeFragment(new MainFragment());
            }
        });
        my.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeFragment(new MyFragment());
            }
        });
        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeFragment(new MessageFragment());
            }
        });
    }
    public void changeFragment(Fragment fragment){
        mFragment = fragment;
        fragmentManager.beginTransaction().replace(R.id.container,mFragment).commit();

    }
    }