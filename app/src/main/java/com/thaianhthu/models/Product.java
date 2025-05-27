package com.thaianhthu.models;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Product implements Serializable {
    private int id;
    private String name;
    private int quantity;
    private double price;
    private String imageId;

    //thuoc tinh nao khong biet truoc duoc thuoc danh muc sp nao
    // thi khi khoi tao phai
    // tao 3 constructors: khong doi so, day du doi so va loai bo cate_id

    private int image_id;

    public Product(int id, String name, int quantity, double price, int image_id) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.image_id = image_id;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    public int getImage_id() {
        return image_id;
    }

    public void setImage_id(int image_id) {
        this.image_id = image_id;
    }

    @NonNull
    @Override
    public String toString() {
        return id+"\t"+name+"\t"+price;
    }


    public Product() {
    }
}


