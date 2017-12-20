package com.bignerdranch.android.recycle.entity;


/**
 * Created by yfs on 5/27 0027.
 */

public class ChatMSG  {

    String msg;
    private Boolean mIsFromMe;
    String mName;
    String mContent;
    String mDate;
    public ChatMSG(String name, String content, String date)
    {
        mName = name;
        mContent = content;
        mDate = date;

    }
    public static ChatMSG get(){
        return new ChatMSG(null,null,null);
    }
    public Boolean getFromMe() {
        return mIsFromMe;
    }

    public void setFromMe(Boolean mIsFromMe) {
        this.mIsFromMe = mIsFromMe;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        mContent = content;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        mDate = date;
    }
}
