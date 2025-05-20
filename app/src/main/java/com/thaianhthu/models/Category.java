package com.thaianhthu.models;

import androidx.annotation.NonNull;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

public class Category {
    private int id;
    private String name;

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

    public int getImage_id() {
        return image_id;
    }

    public void setImage_id(int image_id) {
        this.image_id = image_id;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    private int image_id;
    private ArrayList<Product> products;

    public Category() {
        products = new ArrayList<>();
    }

    public Category(int id, String name, int image_id) {
        this.id = id;
        this.name = name;
        this.image_id = image_id;
        products = new ArrayList<>();

    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
    public void addProduct(Product p) {
        products.add(p);
    }
}
