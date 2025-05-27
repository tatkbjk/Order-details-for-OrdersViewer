package com.example.k22411csampleproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.thaianhthu.models.Category;
import com.thaianhthu.models.ListCategory;
import com.thaianhthu.models.Product;

public class ProductManagementActivity extends AppCompatActivity {
    Spinner spinnerCategory;
    ListCategory listCategory;
    ArrayAdapter<Category> adapterCategory;
    ListView lvProduct;
    ArrayAdapter<Product> adapterProduct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_product_management);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        addViews();
        addEvents();
    }

    private void addEvents() {
        spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Category c = adapterCategory.getItem(i);
                displayProductsByCategory (c);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        lvProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                Product p = adapterProduct.getItem(i);
                displayProductDetailActivity(p);
            }
        });
    }

    private void displayProductDetailActivity(Product p) {
        Intent intent = new Intent(this, ProductDetailActivity.class);
        intent.putExtra("SELECTED_PRODUCT", p);
        startActivity(intent);
    }


    private void displayProductsByCategory(Category c) {
        adapterProduct.clear();
        adapterProduct.addAll(c.getProducts());
    }


    private void addViews() {
        spinnerCategory=findViewById(R.id.spinnerCategory);
        adapterCategory=new ArrayAdapter<>(ProductManagementActivity.this, android.R.layout.simple_spinner_item);
        adapterCategory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(adapterCategory);

        listCategory=new ListCategory();
        listCategory.generate_sample_product_dataset();
        adapterCategory.addAll(listCategory.getCategories());

        lvProduct=findViewById(R.id.lvProduct);
        adapterProduct=new ArrayAdapter<>(ProductManagementActivity.this, android.R.layout.simple_list_item_1);
        lvProduct.setAdapter(adapterProduct);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu_product, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_new_product) {
            Intent intent = new Intent(this, ProductDetailActivity.class);
            startActivity(intent);
            return true;

        } else if (item.getItemId() == R.id.menu_import_data) {
            Toast.makeText(this, "Sample data imported.", Toast.LENGTH_SHORT).show();
            return true;

        } else if (item.getItemId() == R.id.menu_product_help) {
            Toast.makeText(this, "Visit our website for help!", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}