package com.bignerdranch.android.recycle.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bignerdranch.android.recycle.R;
import com.bignerdranch.android.recycle.entity.ProduceMSG;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by asus on 2017/11/23.
 */

public class PaperItemFragment extends Fragment {
    private View view;
    private LinearLayout mReturn;
    private FragmentManager fm;
    private EditText weight;
    private Button add;
    private TextView price;
    private TextView produceName;
    public static String name="0";
    public static int id=0;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        view = inflater.inflate(R.layout.fragment_paper_item,container,false);
        init();
        listener();
        return view;
    }
    public void init(){
        mReturn = (LinearLayout)view.findViewById(R.id.return_linear);
        fm = getActivity().getSupportFragmentManager();
        weight = (EditText)view.findViewById(R.id.weight_edt);
        add = (Button)view.findViewById(R.id.add_btn);
        price = (TextView)view.findViewById(R.id.price_tev);
        produceName = (TextView)view.findViewById(R.id.paper_tev);
    }
    public void listener(){
        mReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fm.popBackStack();
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = produceName.getText().toString();
                Double a = Double.parseDouble(price.getText().toString());
                Double b = Double.parseDouble(weight.getText().toString());
                ProduceMSG produceMSG = new ProduceMSG(id,name,s,b,a,R.drawable.paper);
                produceMSG.save(new SaveListener<String>() {
                    @Override
                        public void done(String s, BmobException e) {
                            if(e==null){
                                Toast.makeText(getActivity(),"加入成功",Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(getActivity(),"创建数据失败：" + e.getMessage(),Toast.LENGTH_SHORT).show();
                            }
                    }
                });
            }
        });
    }
}
