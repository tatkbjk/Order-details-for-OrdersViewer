package com.connectors;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.thaianhthu.models.OrderDetails;

import java.util.ArrayList;

public class OrderDetailsConnector {

    public ArrayList<OrderDetails> getAllOrderDetailsByOrderId(SQLiteDatabase database, int orderId) {
        ArrayList<OrderDetails> datasets = new ArrayList<>();
        StringBuilder builder = new StringBuilder();

        builder.append("SELECT ");
        builder.append("p.Name AS ProductName, ");
        builder.append("od.Quantity, ");
        builder.append("od.Price, ");
        builder.append("od.Discount, ");
        builder.append("od.VAT ");
        builder.append("FROM OrderDetails od ");
        builder.append("JOIN Product p ON od.ProductId = p.Id ");
        builder.append("WHERE od.OrderId = ").append(orderId).append(";");

        Cursor cursor = database.rawQuery(builder.toString(), null);
        while (cursor.moveToNext()) {
            String productName = cursor.getString(0);
            int quantity = cursor.getInt(1);
            double price = cursor.getDouble(2);
            double discount = cursor.getDouble(3);
            double vat = cursor.getDouble(4);

            OrderDetails od = new OrderDetails();
            od.setProductName(productName);
            od.setQuantity(quantity);
            od.setPrice(price);
            od.setDiscount(discount);
            od.setVAT(vat);
            od.setTotalValue(od.getTotalPrice());

            datasets.add(od);
        }
        cursor.close();
        return datasets;
    }
}
