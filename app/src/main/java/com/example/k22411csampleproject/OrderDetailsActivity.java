package com.example.k22411csampleproject;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.connectors.OrderDetailsConnector;
import com.adapters.OrderDetailsAdapter;
import com.thaianhthu.models.OrderDetails;

import java.util.ArrayList;

public class OrderDetailsActivity extends AppCompatActivity {
    ListView lvOrderDetails;
    ArrayList<OrderDetails> list = new ArrayList<>();
    OrderDetailsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_order_details);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        lvOrderDetails = findViewById(R.id.lvOrderDetails);

        int orderId = getIntent().getIntExtra("ORDER_ID", -1);
        SQLiteDatabase db = openOrCreateDatabase("SalesDatabase.sqlite", MODE_PRIVATE, null);
        OrderDetailsConnector connector = new OrderDetailsConnector();
        list = connector.getAllOrderDetailsByOrderId(db, orderId);
        db.close();

        adapter = new OrderDetailsAdapter(this, R.layout.item_order_details, list);
        lvOrderDetails.setAdapter(adapter);
    }
}
