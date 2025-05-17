package com.thaianhthu.models;

import java.io.Serializable;
import java.util.ArrayList;

public class ListCategory implements Serializable {
    private ArrayList<Category> categories;

    public ListCategory() {
        categories = new ArrayList<>();
    }

    public ArrayList<Category> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<Category> categories) {
        this.categories = categories;
    }

    public void addCategory(Category c) {
        categories.add(c);
    }


    public void generate_sample_dataset_category() {
        for (int i = 1; i <= 10; i++) {
            int id = i;
            String name = "Category " + i;
            Category ca = new Category(id, name);
            addCategory(ca);
        }
    }}
