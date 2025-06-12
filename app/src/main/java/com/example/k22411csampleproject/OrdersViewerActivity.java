package com.example.k22411csampleproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.adapters.OrdersViewerAdapter;
import com.connectors.OrderDetailsConnector;
import com.connectors.OrdersViewerConnector;
import com.connectors.SQLiteConnector;
import com.thaianhthu.models.OrdersViewer;

import java.util.ArrayList;

public class OrdersViewerActivity extends AppCompatActivity {
    //Khai bao cac view
    ListView lvOrdersViewer;
    OrdersViewerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_orders_viewer);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        addViews();
    }

    private void addViews() {
        lvOrdersViewer = findViewById(R.id.lvOrdersViewer);
        adapter = new OrdersViewerAdapter(this, R.layout.item_ordersviewer);
        lvOrdersViewer.setAdapter(adapter);

        SQLiteConnector connector = new SQLiteConnector(this);
        OrdersViewerConnector ovc = new OrdersViewerConnector();
        ArrayList<OrdersViewer> dataset = ovc.getAllOdersViewers(connector.openDatabase());
        adapter.addAll(dataset);

        lvOrdersViewer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                OrdersViewer selectedOrder = adapter.getItem(position);
                Intent intent = new Intent(OrdersViewerActivity.this, OrderDetailsActivity.class);
                intent.putExtra("ORDER_ID", selectedOrder.getId());
                startActivity(intent);
            }
        });
    }
}