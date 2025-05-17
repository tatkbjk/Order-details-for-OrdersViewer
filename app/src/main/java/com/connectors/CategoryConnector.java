package com.connectors;

import com.thaianhthu.models.Category;
import com.thaianhthu.models.ListCategory;

import java.util.ArrayList;

public class CategoryConnector {
    ListCategory listCategory;

    public CategoryConnector() {
        listCategory = new ListCategory();
        listCategory.generate_sample_dataset_category();
    }

    public ArrayList<Category> get_all_categories() {
        if (listCategory == null) {
            listCategory = new ListCategory();
            listCategory.generate_sample_dataset_category();
        }
        return listCategory.getCategories();
    }

    // Hàm lọc category theo tên bắt đầu bằng provider (hoặc từ khóa nào đó)
    public ArrayList<Category> get_categories_by_provider(String provider) {
        if (listCategory == null) {
            listCategory = new ListCategory();
            listCategory.generate_sample_dataset_category();
        }
        ArrayList<Category> results = new ArrayList<>();
        for (Category cate : listCategory.getCategories()) {
            if (cate.getName().startsWith(provider)) {
                results.add(cate);
            }
        }
        return results;
    }
}
