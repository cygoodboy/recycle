package com.bignerdranch.android.recycle.fragment;

/**
 * Created by asus on 2017/11/10.
 */

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;

import com.bignerdranch.android.recycle.R;
import com.bignerdranch.android.recycle.activity.MainActivity;
import com.bignerdranch.android.recycle.activity.SearchActivity;

import java.lang.reflect.Field;

/**
 * Created by asus on 2017/11/10.
 */

public class MessageFragment extends Fragment {
    private View view;
    private LinearLayout messageLinear;
    private ImageView messageImv;
    private SearchView mSearchView;
    private LinearLayout mLinearLayout;
    private LinearLayout saleman;
    private LinearLayout buyman;
    private String TAG = "MessageFragment";
    private Fragment fragment;
    private FragmentManager fm;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        view = inflater.inflate(R.layout.message_fragment,container,false);
        init();
        listener();
        return view;
    }

    public void init() {
        messageLinear = (LinearLayout)view.findViewById(R.id.message_linear);
        messageImv = (ImageView) view.findViewById(R.id.message_imv);
        mSearchView = (SearchView)view.findViewById(R.id.contactAndGroup_searchView);
        mLinearLayout = (LinearLayout)view.findViewById(R.id.search_linear);
        saleman = (LinearLayout)view.findViewById(R.id.saleman_linearLayout);
        buyman = (LinearLayout)view.findViewById(R.id.buyman_linearLayout);
        fm = getActivity().getSupportFragmentManager();
        changeSearchViewStyle();
    }
    public void listener() {
        messageLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                messageImv.setVisibility(View.INVISIBLE);
            }
        });
        mLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (getActivity(), SearchActivity.class);
                startActivity(intent);
            }
        });
        saleman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = MainActivity.newInstance(getContext(),1);
                startActivity(intent);

            }
        });
        buyman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = MainActivity.newInstance(getContext(),2);
                startActivity(intent);
            }
        });
    }

    private void changeSearchViewStyle() {
        if (Build.VERSION.SDK_INT < 21) {
            return;
        }
        try {
            Class<?> searchViewClass = mSearchView.getClass();
            Field field = searchViewClass.getDeclaredField("mSearchPlate");
            field.setAccessible(true);
            View view = (View) field.get(mSearchView);
            view.setBackground(null);
            field.setAccessible(false);
        } catch (NoSuchFieldException ne) {
            Log.e(TAG, "get field ", ne);
        } catch (IllegalAccessException iae) {
            Log.e(TAG, "field.get() ", iae);
        }
    }
}

