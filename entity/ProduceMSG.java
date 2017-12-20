package com.bignerdranch.android.recycle.entity;

import cn.bmob.v3.BmobObject;

/**
 * Created by asus on 2017/12/9.
 */

public class ProduceMSG extends BmobObject {
    int id;
    String name;
    String produceName;
    double price;
    double weight;
    int photoId;
    public  ProduceMSG(){
    }
    public ProduceMSG(int id, String name, String produceName, double price, double weight,int photoId){
        this.id = id;
        this.name = name;
        this.produceName = produceName;
        this.price = price;
        this.weight = weight;
        this.photoId = photoId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProduceName() {
        return produceName;
    }

    public void setProduceName(String produceName) {
        this.produceName = produceName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getPhotoId() {
        return photoId;
    }

    public void setPhotoId(int photoId) {
        this.photoId = photoId;
    }
}
