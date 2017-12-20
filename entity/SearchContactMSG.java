package com.bignerdranch.android.recycle.entity;

/**
 * Created by asus on 2017/11/12.
 */

public class SearchContactMSG {
    String mName;
    int mPhoneId;

    public SearchContactMSG(String name,int phoneId) {
        mName = name;
        mPhoneId = phoneId;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public int getPhoneId() {
        return mPhoneId;
    }

    public void setPhoneId(int phoneId) {
        mPhoneId = phoneId;
    }
}
