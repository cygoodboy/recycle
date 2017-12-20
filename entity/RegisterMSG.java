package com.bignerdranch.android.recycle.entity;

import cn.bmob.v3.BmobObject;

/**
 * 项目名：  SmartApp
 * 包名：    app.cddic.com.smarter.entity
 * 文件名：  RegisterMSG
 * 创建者：
 * 创建时间： 2017/4/27 15:48
 * 描述：
 */

public class RegisterMSG extends BmobObject {
    private int id;
    private String loginName;
    private String sex;
    private String email;
    private String password;
    private String phoneNum;

    public RegisterMSG(int s1, String s2, String s3, String s4, String s5, String s6) {

        id = s1;
        loginName = s2;
        sex = s3;
        email = s4;
        password = s5;
        phoneNum = s6;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
