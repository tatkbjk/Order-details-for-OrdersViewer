package com.example.k22411csampleproject;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.adapters.PaymentMethodAdapter;
import com.connectors.PaymentMethodConnector;
import com.connectors.SQLiteConnector;
import com.thaianhthu.models.ListPaymentMethod;
import com.thaianhthu.models.PaymentMethod;

public class PaymentMethodActivity extends AppCompatActivity {
    ListView lvPaymentMethod;
    PaymentMethodAdapter adapter;
    ListPaymentMethod lpm;

    //Khai bao cac ham

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_payment_method);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        addViews();
    }

    private void addViews() {
        lvPaymentMethod = findViewById(R.id.lvPaymentMethod);
        adapter = new PaymentMethodAdapter(PaymentMethodActivity.this, R.layout.item_payment_method);
        lvPaymentMethod.setAdapter(adapter);

        // Lấy dữ liệu từ SQLite
        SQLiteConnector sqliteConnector = new SQLiteConnector(this);
        SQLiteDatabase db = sqliteConnector.openDatabase();
        PaymentMethodConnector connector = new PaymentMethodConnector();
        lpm = connector.getAllPaymentMethods(db);

        // Nếu không có dữ liệu, sinh dữ liệu mẫu vào bảng PaymentMethod
        if (lpm.getPaymentMethods().isEmpty()) {
            db.execSQL("INSERT INTO PaymentMethod(id, name, description) VALUES (1, 'INTERNET BANKING', 'Thanh toán bằng tài khoản ngân hàng')");
            db.execSQL("INSERT INTO PaymentMethod(id, name, description) VALUES (2, 'COD', 'Thanh toán khi nhận hàng')");
            db.execSQL("INSERT INTO PaymentMethod(id, name, description) VALUES (3, 'MOMO', 'Thanh toán bằng ví điện tử Momo')");
            db.execSQL("INSERT INTO PaymentMethod(id, name, description) VALUES (4, 'CASH', 'Thanh toán bằng tiền mặt tại quầy')");
            lpm = connector.getAllPaymentMethods(db);
        }
        adapter.addAll(lpm.getPaymentMethods());
    }
}