package com.bignerdranch.android.recycle.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bignerdranch.android.recycle.R;
import com.bignerdranch.android.recycle.entity.SearchContactMSG;

import java.util.List;

/**
 * Created by asus on 2017/11/12.
 */

public class SearchItemAdapter extends BaseAdapter {
    private Context mContext;
    private List<SearchContactMSG> mSearchContactMSGList;
    public SearchItemAdapter(Context context) {
        super();
        mContext = context;
    }
    public SearchItemAdapter(Context context, List<SearchContactMSG> searchContactMSGList)
    {
        this(context);
        mSearchContactMSGList = searchContactMSGList;
    }

    @Override
    public int getCount() {
        return mSearchContactMSGList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_for_search_activity,viewGroup,false);
            ImageView imageView = (ImageView)view.findViewById(R.id.ivg);
            imageView.setImageResource(mSearchContactMSGList.get(i).getPhoneId());
            TextView textView = (TextView)view.findViewById(R.id.name_textView);
            textView.setText(mSearchContactMSGList.get(i).getName());
        }

        return view;
    }
}


