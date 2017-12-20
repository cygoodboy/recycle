package com.bignerdranch.android.recycle.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import com.bignerdranch.android.recycle.R;
import com.bignerdranch.android.recycle.fragment.RubbishItemFragment;

public class ItemActivity extends FragmentActivity {
    private static final String ARG_LOCATION = "location";
    private Fragment fragment;
    private FragmentManager fm = getSupportFragmentManager();
    public static int type = Type.RUBBISH;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        initFragment(type);
        if(fragment!=null){
            fm.beginTransaction().add(R.id.item_container,fragment).commit();
        }
    }
    public static Intent newInstance(Context context,String s){
        Intent intent = new Intent(context,ItemActivity.class);
        intent.putExtra(ARG_LOCATION,s);
        return intent;
    }
    private void initFragment(int fragmentType) {
        switch (fragmentType) {
            case Type.RUBBISH:
                fragment = RubbishItemFragment.newInstance(getIntent().getStringExtra(ARG_LOCATION));
                break;
        }
        }
    public static class Type {
        public static final int RUBBISH = 0;
        public static final int SECONDHAND = 1;
        public static final int MARKET = 2;
    }
}
