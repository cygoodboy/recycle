package com.bignerdranch.android.recycle.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bignerdranch.android.recycle.R;
import com.bignerdranch.android.recycle.adapter.ScrapAdapter;
import com.bignerdranch.android.recycle.entity.ProduceMSG;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobBatch;
import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BatchResult;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListListener;

import static com.bignerdranch.android.recycle.adapter.ScrapAdapter.getCheckState;

/**
 * Created by asus on 2017/12/9.
 */

public class ScrapFrameFragment extends Fragment {
    private View view;
    private ScrapAdapter mScrapAdapter;
    private List<ProduceMSG> mProduceMSGList = new ArrayList<>();
    private List<ProduceMSG> deleteList = new ArrayList<>();
    private TextView edit;
    private TextView sum;
    private LinearLayout mReturn;
    private LinearLayout linearLayout1;
    private LinearLayout linearLayout2;
    private Button selectAll1;
    private Button selectAll2;
    private Button delect;
    private Button account;
    private ListView mListView;
    private int mListLength;
    private double money;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        view = inflater.inflate(R.layout.fragmnet_scrap_frame,container,false);
        Bmob.initialize(getContext(), "165911d422b05bd3af254c5cfe04c813");
        init();
        listener();
        return view;
    }

    public void init() {

        edit = (TextView)view.findViewById(R.id.edit_tev);
        sum = (TextView)view.findViewById(R.id.sum_tev);
        linearLayout1 = (LinearLayout)view.findViewById(R.id.linear1);
        linearLayout2 = (LinearLayout)view.findViewById(R.id.linear2);
        mReturn = (LinearLayout)view.findViewById(R.id.return_linear);
        selectAll1 = (Button)view.findViewById(R.id.selectAll1_btn);
        selectAll2 = (Button)view.findViewById(R.id.selectAll2_btn);
        delect = (Button)view.findViewById(R.id.delete_btn);
        account = (Button)view.findViewById(R.id.account_btn);
        mListView = (ListView)view.findViewById(R.id.scrap_listView);
        linearLayout2.setVisibility(View.GONE);
        BmobQuery<ProduceMSG> query = new BmobQuery<ProduceMSG>();
        query.setLimit(50);
        query.findObjects(new FindListener<ProduceMSG>() {
            @Override
            public void done(List<ProduceMSG> list, BmobException e) {
                if (e==null){
                    mListLength = list.size();
                    mProduceMSGList = list;
                    money = 0;
                    for(int i=0;i<mProduceMSGList.size();i++){
                        money = money+mProduceMSGList.get(i).getPrice()*mProduceMSGList.get(i).getWeight();
                    }
                    BigDecimal bg = new BigDecimal(money);
                    money = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                    sum.setText("合计："+Double.toString(money)+"元");
                    mScrapAdapter = new ScrapAdapter(getContext(),mProduceMSGList);
                    mListView.setAdapter(mScrapAdapter);

                }else {
                    Toast.makeText(getContext(),"创建数据失败：" + e.getMessage(),Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    public void listener(){
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edit.getText().toString().equals("编辑")){
                    linearLayout1.setVisibility(View.GONE);
                    linearLayout2.setVisibility(View.VISIBLE);
                    edit.setText("完成");
                }else{
                        linearLayout1.setVisibility(View.VISIBLE);
                        linearLayout2.setVisibility(View.GONE);
                        edit.setText("编辑");
                        money = 0;
                        for(int i=0;i<mProduceMSGList.size();i++){
                            money = money+mProduceMSGList.get(i).getPrice()*mProduceMSGList.get(i).getWeight();
                        }
                        BigDecimal bg = new BigDecimal(money);
                        money = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                        sum.setText("合计："+Double.toString(money)+"元");
                }

            }
        });
        selectAll1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < mProduceMSGList.size(); i++) {
                    getCheckState().put(i, true);
                }
                dataChanged();
            }
        });
        selectAll2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < mProduceMSGList.size(); i++) {
                    getCheckState().put(i, true);
                }
                dataChanged();
            }
        });
        delect.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
                                          for (int i = mListLength - 1; i >= 0; i--) {
                                              if (getCheckState().get(i)) {
                                                  deleteList.add(mProduceMSGList.get(i));
                                                  mProduceMSGList.remove(i);

                                              }
                                          }
                                          mListLength = mProduceMSGList.size();
                                          mScrapAdapter = new ScrapAdapter(getContext(), mProduceMSGList);
                                          mListView.setAdapter(mScrapAdapter);
                                          List<BmobObject> persons = new ArrayList<BmobObject>();
                                          for (int i = 0; i < deleteList.size(); i++) {
                                              ProduceMSG produceMSG = new ProduceMSG();
                                              produceMSG.setObjectId(deleteList.get(i).getObjectId());
                                              persons.add(produceMSG);
                                          }
                                          new BmobBatch().deleteBatch(persons).doBatch(new QueryListListener<BatchResult>() {
                                              @Override
                                              public void done(List<BatchResult> list, BmobException e) {
                                                  if (e == null) {
                                                      for (int i = 0; i < list.size(); i++) {
                                                          BatchResult result = list.get(i);
                                                          BmobException ex = result.getError();
                                                          if (ex == null) {
                                                              Log.i("test", "第" + i + "个数据批量删除成功");
                                                          } else {
                                                              Log.i("test", "第" + i + "个数据批量删除失败：" + ex.getMessage() + "," + ex.getErrorCode());
                                                          }
                                                      }
                                                  } else {
                                                      Toast.makeText(getActivity(), "创建数据失败：" + e.getMessage(), Toast.LENGTH_SHORT).show();
                                                  }
                                              }
                                          });
                                      }
                                  });
                mReturn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getActivity().finish();
                    }
                });
            }

    public void dataChanged() {
        mScrapAdapter.notifyDataSetChanged();
    }

}