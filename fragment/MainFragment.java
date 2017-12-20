package com.bignerdranch.android.recycle.fragment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bignerdranch.android.recycle.R;
import com.bignerdranch.android.recycle.activity.ItemActivity;

import java.lang.reflect.Field;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by asus on 2017/11/3.
 */

public class MainFragment extends Fragment {
    private String TAG = "MainFragment";
    private Fragment fragment;
    private FragmentManager fm ;
    private ImageView left;
    private ImageView right;
    private TextView name;
    private LinearLayout picture;
    private Spinner location;
    private LinearLayout rubbish;
    private SearchView mSearchView;
    private View view;
    private int[] image = {R.drawable.newspaper,R.drawable.computer,R.drawable.air_blower,R.drawable.water_bottle};
    private String[] imageName = {"旧报纸","二手电脑","二手吹风机","废水瓶"};
    private int imageIndex=0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
         view = inflater.inflate(R.layout.main_fragment,container,false);
         init();
         listener();
         adapter();
        return view;

    }

    public void init()
    {
        fm = getActivity().getSupportFragmentManager();
        left = (ImageView) view.findViewById(R.id.left_imv);
        right = (ImageView)view.findViewById(R.id.right_imv);
        name = (TextView)view.findViewById(R.id.name_textView);
        picture = (LinearLayout)view.findViewById(R.id.picture_linearLayout);
        location = (Spinner)view.findViewById(R.id.location_spn);
        rubbish = (LinearLayout)view.findViewById(R.id.rubbish_linear);
        mSearchView = (SearchView)view.findViewById(R.id.searchView);
        changeSearchViewStyle();
        int id = mSearchView.getContext().getResources().getIdentifier("android:id/search_src_text", null, null);
        TextView textView = (TextView) mSearchView.findViewById(id);
        android.widget.LinearLayout.LayoutParams layoutParams = (android.widget.LinearLayout.LayoutParams) textView.getLayoutParams();
        layoutParams.bottomMargin = -6;
        textView.setLayoutParams(layoutParams);
    }
    public void listener() {
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (imageIndex != 0) {
                    imageIndex--;
                } else {
                    imageIndex = image.length - 1;
                }
                picture.setBackgroundResource(image[imageIndex]);
                name.setText(imageName[imageIndex]);
            }
        });
        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (imageIndex != image.length - 1) {
                    imageIndex++;
                } else {
                    imageIndex = 0;
                }

                picture.setBackgroundResource(image[imageIndex]);
                name.setText(imageName[imageIndex]);
            }
        });
        rubbish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Intent intent = ItemActivity.newInstance(getActivity(),(String) location.getSelectedItem());
               startActivity(intent);
            }
        });
         final Handler handler = new Handler() {
            public void handleMessage(Message msg) {
                if (msg.what == 1) {
                    if (imageIndex != image.length - 1) {
                        imageIndex++;
                    } else {
                        imageIndex = 0;
                    }

                    picture.setBackgroundResource(image[imageIndex]);
                    name.setText(imageName[imageIndex]);
                }
                super.handleMessage(msg);
            }

            ;
        };
         Timer timer = new Timer();
         TimerTask task = new TimerTask() {
            @Override
            public void run() {
                // 需要做的事:发送消息
                Message message = new Message();
                message.what = 1;
                handler.sendMessage(message);
            }
        };
        timer.schedule(task,2000,2000);
    }
    public void adapter(){
        String[] a= {"成都","重庆","上海","北京"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item,a);
        location.setAdapter(adapter);
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
