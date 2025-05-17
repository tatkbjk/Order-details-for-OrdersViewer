package com.thaianhthu.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class ListProduct implements Serializable {
    private ArrayList<Product> products;

    public ListProduct() {
        products = new ArrayList<>();
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public void addProduct(Product p) {
        products.add(p);
    }

    // Hàm tạo danh sách sản phẩm mẫu
    public void generateSampleDataset() {
        Random random = new Random();
        for (int i = 1; i <= 100; i++) {
            int id = i;
            String name = "Sản phẩm " + i;
            int quantity = random.nextInt(50) + 1; // 1 → 50
            double price = (random.nextInt(1000) + 100) * 1000; // giá từ 100k → 1.1 triệu
            int cate_id = random.nextInt(5) + 1; // giả sử có 5 danh mục
            String description = "Mô tả sản phẩm " + i;

            Product p = new Product(id, name, quantity, price, cate_id, description);
            addProduct(p);
        }
    }
}
