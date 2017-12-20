package com.bignerdranch.android.recycle.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import com.bignerdranch.android.recycle.R;
import com.bignerdranch.android.recycle.fragment.RegisterFragment;

public class RegisterActivity extends FragmentActivity {
    private FragmentManager fragmentManager=getSupportFragmentManager();
    private Fragment mFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mFragment = fragmentManager.findFragmentById(R.id.register_container);
        if(mFragment==null){
            mFragment = new RegisterFragment();
            fragmentManager.beginTransaction().add(R.id.register_container,mFragment).commit();

        }
//        init();
//        listener();

    }

    public void init() {
    }
    public void listener(){}
}
