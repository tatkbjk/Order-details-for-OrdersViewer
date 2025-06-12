package com.connectors;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.thaianhthu.models.ListPaymentMethod;
import com.thaianhthu.models.PaymentMethod;

public class PaymentMethodConnector {
    /**
     * Lấy toàn bộ phương thức thanh toán từ SQLite và trả về ListPaymentMethod
     * @param database SQLiteDatabase
     * @return ListPaymentMethod
     */
    public ListPaymentMethod getAllPaymentMethods(SQLiteDatabase database) {
        ListPaymentMethod list = new ListPaymentMethod();
        Cursor cursor = database.rawQuery("SELECT * FROM PaymentMethod", null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String description = cursor.getString(2);

            PaymentMethod pm = new PaymentMethod();
            pm.setId(id);
            pm.setName(name);
            pm.setDescription(description);

            // Thêm phương thức thanh toán vào danh sách
            list.getPaymentMethods().add(pm);
        }
        cursor.close();
        return list;
    }
}
