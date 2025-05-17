package com.connectors;

import com.thaianhthu.models.Product;
import com.thaianhthu.models.ListProduct;

import java.util.ArrayList;

public class ProductConnector {
    ListProduct listProduct;

    public ProductConnector() {
        listProduct = new ListProduct();
        listProduct.generateSampleDataset();
    }

    public ArrayList<Product> getAllProducts() {
        if (listProduct == null) {
            listProduct = new ListProduct();
            listProduct.generateSampleDataset();
        }
        return listProduct.getProducts();
    }

    public ArrayList<Product> getProductsByCategory(int cateId) {
        if (listProduct == null) {
            listProduct = new ListProduct();
            listProduct.generateSampleDataset();
        }
        ArrayList<Product> results = new ArrayList<>();
        for (Product p : listProduct.getProducts()) {
            if (p.getCate_id() == cateId) {
                results.add(p);
            }
        }
        return results;
    }
}
