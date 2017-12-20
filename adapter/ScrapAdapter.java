package com.bignerdranch.android.recycle.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bignerdranch.android.recycle.R;
import com.bignerdranch.android.recycle.entity.ProduceMSG;

import java.util.HashMap;
import java.util.List;

/**
 * Created by asus on 2017/12/9.
 */

public class ScrapAdapter extends BaseAdapter {
    private Context mContext;
    private List<ProduceMSG> mProduceMSGList;
    private static HashMap<Integer, Boolean> mCheckState;
    public ScrapAdapter(Context context) {
        super();
        mContext = context;
    }
    public ScrapAdapter(Context context, List<ProduceMSG> ProduceMSGList)
    {
        this(context);
        mProduceMSGList = ProduceMSGList;
        mCheckState = new HashMap<Integer, Boolean>();
        initDate();
    }
    private void initDate() {
        for (int i = 0; i <  mProduceMSGList .size(); i++) {
            getCheckState().put(i, false);
        }
    }

    @Override
    public int getCount() {
        return mProduceMSGList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_for_scrap_frame,viewGroup,false);

        }
        ViewHolder holder = new ViewHolder();
        holder.mCheckBox = (CheckBox)view.findViewById(R.id.item_checkBox);
        holder.photo = (ImageView)view.findViewById(R.id.phone_imv);
        holder.mName = (TextView)view.findViewById(R.id.name_textView);
        holder.mPrice = (TextView)view.findViewById(R.id.price_tev);
        holder.mWeight = (TextView)view.findViewById(R.id.weight_tev);
        view.setTag(holder);
        holder.mCheckBox.setChecked(getCheckState().get(position));
        holder.photo.setImageResource(mProduceMSGList.get(position).getPhotoId());
        holder.mName.setText(mProduceMSGList.get(position).getProduceName());
        holder.mWeight.setText(Double.toString(mProduceMSGList.get(position).getWeight()));
        holder.mPrice.setText(Double.toString(mProduceMSGList.get(position).getPrice()));
        holder.mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                getCheckState().put(position,isChecked);
                notifyDataSetChanged();
            }
        });
        return view;
    }


    public static  HashMap<Integer, Boolean> getCheckState(){
        return mCheckState;
    }
    public static class ViewHolder {
        CheckBox mCheckBox;
        ImageView photo;
        TextView mName;
        TextView mWeight;
        TextView mPrice;

    }
}
