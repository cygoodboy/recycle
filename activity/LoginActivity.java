package com.bignerdranch.android.recycle.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bignerdranch.android.recycle.R;
import com.bignerdranch.android.recycle.entity.RegisterMSG;
import com.bignerdranch.android.recycle.fragment.DrygoodsItemFragment;
import com.bignerdranch.android.recycle.fragment.MetalItemFragment;
import com.bignerdranch.android.recycle.fragment.PaperItemFragment;
import com.bignerdranch.android.recycle.fragment.PlasticItemFragment;

import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class LoginActivity extends AppCompatActivity {
    private Button loginButton;
    private Button regieterButton;
    private EditText id;
    private EditText key;
    private int i;
    private String j;
    private String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);
        Bmob.initialize(this, "165911d422b05bd3af254c5cfe04c813");
        init();
        listener();
        adapter();
    }
    public void init()
    {
        loginButton = (Button)findViewById(R.id.login_btn);
        id = (EditText)findViewById(R.id.id_edt);
        key = (EditText)findViewById(R.id.key_edt);
        regieterButton = (Button)findViewById(R.id.register_btn);
        key.setTransformationMethod(PasswordTransformationMethod.getInstance());
    }
    public void listener(){
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String regexEmail = "\\w+@\\w+(\\.\\w{2,3})*\\.\\w{2,3}";
                String regexTel = "\\d{11}";
                String regexId = "\\d{8}";
                if(!id.getText().toString().matches(regexEmail)&&!id.getText().toString().matches(regexTel)&&!id.getText().toString().matches(regexId)){
                    Toast.makeText(LoginActivity.this,"输入格式不对，请重新输入",Toast.LENGTH_SHORT).show();
                }
                else{
                    if (id.getText().toString().matches(regexId)){
                        BmobQuery<RegisterMSG> query = new BmobQuery<RegisterMSG>();
                        query.addWhereEqualTo("id",Integer.parseInt(id.getText().toString()));
                        query.findObjects(new FindListener<RegisterMSG>() {
                            @Override
                            public void done(List<RegisterMSG> list, BmobException e) {
                                if(e==null){
                                    i = list.get(0).getId();
                                    Log.i("smileID", Integer.toString(i));
                                    j = list.get(0).getPassword();
                                    Log.i("smileKey",j);
                                    name = list.get(0).getLoginName();

                                    if(id.getText().toString().equals(Integer.toString(i))&&key.getText().toString().equals(j))
                                    {
                                        PaperItemFragment.id = Integer.parseInt(id.getText().toString());
                                        PaperItemFragment.name = name;
                                        DrygoodsItemFragment.id = Integer.parseInt(id.getText().toString());
                                        DrygoodsItemFragment.name = name;
                                        MetalItemFragment.id = Integer.parseInt(id.getText().toString());
                                        MetalItemFragment.name = name;
                                        PlasticItemFragment.id = Integer.parseInt(id.getText().toString());
                                        PlasticItemFragment.name = name;
                                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                        startActivity(intent);
                                    }
                                    else {
                                        Toast.makeText(LoginActivity.this,"账号和密码不匹配，请重新输入",Toast.LENGTH_SHORT).show();;
                                    }
                                }else{
                                    Toast.makeText(LoginActivity.this,"创建数据失败：" + e.getMessage(),Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                    if (id.getText().toString().matches(regexTel)){
                        BmobQuery<RegisterMSG> query = new BmobQuery<RegisterMSG>();
                        query.addWhereEqualTo("phoneNum",id.getText().toString());
                        query.findObjects(new FindListener<RegisterMSG>() {
                            @Override
                            public void done(List<RegisterMSG> list, BmobException e) {
                                if(e==null){
                                    String phone = list.get(0).getPhoneNum();
                                    Log.i("smileID", Integer.toString(i));
                                    j = list.get(0).getPassword();
                                    Log.i("smileKey",j);
                                    name = list.get(0).getLoginName();
                                    if(id.getText().toString().equals(phone)&&key.getText().toString().equals(j))
                                    {

                                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                        startActivity(intent);
                                    }
                                    else {
                                        Toast.makeText(LoginActivity.this,"电话和密码不匹配，请重新输入",Toast.LENGTH_SHORT).show();;
                                    }
                                }else{
                                    Toast.makeText(LoginActivity.this,"创建数据失败：" + e.getMessage(),Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                    if (id.getText().toString().matches(regexEmail)){
                        BmobQuery<RegisterMSG> query = new BmobQuery<RegisterMSG>();
                        query.addWhereEqualTo("email",id.getText().toString());
                        query.findObjects(new FindListener<RegisterMSG>() {
                            @Override
                            public void done(List<RegisterMSG> list, BmobException e) {
                                if(e==null){
                                    String email = list.get(0).getEmail();
                                    Log.i("smileEmail", Integer.toString(i));
                                    j = list.get(0).getPassword();
                                    Log.i("smileKey",j);
                                    if(id.getText().toString().equals(email)&&key.getText().toString().equals(j))
                                    {
                                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                        startActivity(intent);
                                    }
                                    else {
                                        Toast.makeText(LoginActivity.this,"邮箱和密码不匹配，请重新输入",Toast.LENGTH_SHORT).show();;
                                    }
                                }else{
                                    Toast.makeText(LoginActivity.this,"创建数据失败：" + e.getMessage(),Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }

                }



            }
        });
        regieterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
    public void adapter(){

    }

}


