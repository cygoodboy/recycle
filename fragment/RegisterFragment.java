package com.bignerdranch.android.recycle.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.bignerdranch.android.recycle.R;
import com.bignerdranch.android.recycle.activity.LoginActivity;
import com.bignerdranch.android.recycle.adapter.TestArrayAdapter;
import com.bignerdranch.android.recycle.entity.RegisterMSG;

import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;



/**
 * Created by asus on 2017/11/4.
 */

public class RegisterFragment extends Fragment {
    private View view;
    private Spinner sex;
    private Button check;
    private Button register;
    private Button getIdentify;
    private EditText loginId;
    private EditText name;
    private EditText email;
    private EditText loginKey;
    private EditText sureKey;
    private EditText phone;
    private EditText phoneIdentify;
    private Fragment fragment;
    private FragmentManager fm;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        view = inflater.inflate(R.layout.fragment_register_user,container,false);
        fm = getActivity().getSupportFragmentManager();
        Bmob.initialize(getContext(), "165911d422b05bd3af254c5cfe04c813");
       // BmobSMS.initialize(getContext(),"165911d422b05bd3af254c5cfe04c813");
        BmobUser bu = new BmobUser();
        bu.setUsername("易聪");
        bu.setPassword("12345678");
        bu.setEmail("1179984132@qq.com");
//注意：不能用save方法进行注册
        bu.signUp(new SaveListener<BmobUser>() {
            @Override
            public void done(BmobUser bmobUser, BmobException e) {
                if(e==null){
                   Toast.makeText(getContext(),"注册成功:" +bmobUser.toString(),Toast.LENGTH_SHORT).show();
                }else{
                }
            }

        });
        init();
        listener();
        adapter();
        return view;
    }
    public void init(){
       loginId = (EditText)view.findViewById(R.id.loginId_edit);
       name = (EditText)view.findViewById(R.id.loginName_edit);
       email = (EditText)view.findViewById(R.id.mail_edit);
       check = (Button)view.findViewById(R.id.check_btn);
       register = (Button)view.findViewById(R.id.nowRegister_btn);
       loginKey = (EditText)view.findViewById(R.id.loginKey_edit);
       sureKey = (EditText)view.findViewById(R.id.sureKey_edit);
       loginKey.setTransformationMethod(PasswordTransformationMethod.getInstance());
       sureKey.setTransformationMethod(PasswordTransformationMethod.getInstance());
       phone = (EditText)view.findViewById(R.id.phone_edit);
       phoneIdentify = (EditText)view.findViewById(R.id.phoneIdentify_edit);
       getIdentify = (Button)view.findViewById(R.id.getIdentify_btn);
    }
    public void listener(){
        final String regexId = "\\d{8}";
        final String regexPhone = "\\d{11}";
        final String regexEmail = "\\w+@\\w+(\\.\\w{2,3})*\\.\\w{2,3}";
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!loginId.getText().toString().matches(regexId)){
                    Toast.makeText(getActivity(),"请输入8位数字",Toast.LENGTH_SHORT).show();
                }
                else {
                    BmobQuery<RegisterMSG> query = new BmobQuery<RegisterMSG>();
                    query.addWhereEqualTo("id",Integer.parseInt(loginId.getText().toString()));
                    query.findObjects(new FindListener<RegisterMSG>() {
                        @Override
                        public void done(List<RegisterMSG> list, BmobException e) {
                            if (e == null) {
                                if (list.size() != 0) {
                                    Toast.makeText(getActivity(), "账号已存在，重新输入", Toast.LENGTH_SHORT).show();
                                }else {
                                    Toast.makeText(getActivity(),"输入有效",Toast.LENGTH_SHORT).show();
                                }
                            }else{
                                Toast.makeText(getActivity(),"创建数据失败：" + e.getMessage(),Toast.LENGTH_SHORT).show();
                            }
                        }
                        });
                }
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!loginId.getText().toString().matches(regexId)){
                    Toast.makeText(getActivity(),"账号格式不对，请重新输入",Toast.LENGTH_SHORT).show();

                }else {
                    BmobQuery<RegisterMSG> query = new BmobQuery<RegisterMSG>();
                    query.addWhereEqualTo("id",Integer.parseInt(loginId.getText().toString()));
                    query.findObjects(new FindListener<RegisterMSG>() {
                        @Override
                        public void done(List<RegisterMSG> list, BmobException e) {
                            if(e==null){
                                if (list.size()!=0){
                                    Toast.makeText(getActivity(),"账号已存在，重新输入",Toast.LENGTH_SHORT).show();
                                } else if (!email.getText().toString().matches(regexEmail)){
                                    Toast.makeText(getActivity(),"邮箱格式不对，重新输入",Toast.LENGTH_SHORT).show();

                                }else if (loginKey.length()==0){
                                    Toast.makeText(getActivity(),"密码不能为空",Toast.LENGTH_SHORT).show();
                                }else if (!loginKey.getText().toString().equals(sureKey.getText().toString())) {
                                        Toast.makeText(getActivity(),"两次密码不一样，重新输入",Toast.LENGTH_SHORT).show();
                                }else if (!phone.getText().toString().matches(regexPhone)) {
                                        Toast.makeText(getActivity(), "手机号格式不对，重新输入", Toast.LENGTH_SHORT).show();
                                }else if (phone.getText().length()==0) {
                                    Toast.makeText(getActivity(),"手机号不能为空",Toast.LENGTH_SHORT).show();
                                }else if (phoneIdentify.getText().length()==0) {
                                    Toast.makeText(getActivity(),"手机验证码不能为空",Toast.LENGTH_SHORT).show();}
                                    else{
                                    BmobSMS.verifySmsCode(phone.getText().toString(),phoneIdentify.getText().toString(),new UpdateListener() {

                                        public void done(BmobException ex) {
                                            if (ex == null) {// 短信验证码已验证成功
                                                Toast.makeText(getActivity(),"注册成功",Toast.LENGTH_SHORT).show();
                                                RegisterMSG registerMSG = new RegisterMSG(Integer.parseInt(loginId.getText().toString()),
                                                        name.getText().toString(),
                                                        (String)sex.getSelectedItem(),
                                                        email.getText().toString(),
                                                        loginKey.getText().toString(),
                                                        phone.getText().toString());
                                                registerMSG.save(new SaveListener<String>() {
                                                    @Override
                                                    public void done(String s, BmobException e) {
                                                        if(e==null){
                                                            Toast.makeText(getActivity(),"添加数据成功，返回objectId为："+s,Toast.LENGTH_SHORT).show();
                                                        }else{
                                                            Toast.makeText(getActivity(),"创建数据失败：" + e.getMessage(),Toast.LENGTH_SHORT).show();
                                                        }
                                                    }
                                                });

                                                Intent intent = new Intent(getActivity(), LoginActivity.class);
                                                startActivity(intent);

                                            } else {
                                                System.out.println("bmob验证失败：code ="
                                                        + ex.getErrorCode() + ",msg = "
                                                        + ex.getLocalizedMessage());
                                            }
                                        }
                                    });
                                }


                            }else{
                                Toast.makeText(getActivity(),"创建数据失败：" + e.getMessage(),Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }

            }
        });
        getIdentify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BmobSMS.requestSMSCode(phone.getText().toString(), "register", new QueryListener<Integer>() {
                    @Override
                    public void done(Integer integer, BmobException e) {
                        if (e==null){
                            Toast.makeText(getContext(),"验证码发送成功",Toast.LENGTH_SHORT).show();
                        }
                        else {

                        }
                    }
                });
            }
        });
    }

    public void adapter(){
        sex = (Spinner)view.findViewById(R.id.registerUser_sex_spinner);
        String[] data = {"男","女"};
        ArrayAdapter<String> adapter = new TestArrayAdapter(getActivity(),data);
        sex.setAdapter(adapter);
    }
}
