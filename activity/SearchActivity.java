package com.bignerdranch.android.recycle.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import com.bignerdranch.android.recycle.R;
import com.bignerdranch.android.recycle.adapter.SearchItemAdapter;
import com.bignerdranch.android.recycle.entity.SearchContactMSG;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends FragmentActivity {
    private String TAG = "SearchActivity";
    private SearchView mSearchView;
    private ListView mListView ;
    private List<SearchContactMSG> mList = new ArrayList<>();
    private List<SearchContactMSG> findList = new ArrayList<>();
    private String[] mStrs = {"张三", "李四", "张飞", "王五","王武"};
    private int[] pictureId = {R.mipmap.buyman,R.mipmap.saleman,R.mipmap.buyman,R.mipmap.saleman,R.mipmap.buyman};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        init();
        listener();
        changeSearchViewStyle();
    }
    public void init() {
        mSearchView = (SearchView)findViewById(R.id.contact_searchView);
        mListView = (ListView)findViewById(R.id.search_listView);
        mListView.setTextFilterEnabled(true);
        for (int i = 0; i < mStrs.length; i++)
            mList.add(new SearchContactMSG(mStrs[i], pictureId[i]));
    }

    public void listener() {
        // 设置搜索文本监听
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            // 当点击搜索按钮时触发该方法
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            // 当搜索内容改变时触发该方法
            @Override
            public boolean onQueryTextChange(String newText) {
                findList.clear();
                if(!TextUtils.isEmpty(newText)){
                    for(int i = 0; i < mList.size(); i++) {
                        SearchContactMSG searchContactMSG = mList.get(i);
                        if(searchContactMSG.getName().contains(newText)) {
                            findList.add(searchContactMSG);
                        }
                    }
                    Adapter adapter = new SearchItemAdapter(SearchActivity.this,findList);
                    mListView.setAdapter((ListAdapter) adapter);
                }
                else {
                    mListView.setAdapter(null);
                }

                return false;
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
