package com.connectors;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.thaianhthu.models.Customer;
import com.thaianhthu.models.ListCustomer;

import java.util.ArrayList;

public class CustomerConnector {
    ListCustomer listCustomer;
    public CustomerConnector()
    {
        listCustomer =new ListCustomer();
        listCustomer.generate_sample_dataset();
    }
    public ArrayList<Customer> get_all_customeṛ̣()
    {
        if(listCustomer==null)
        {
            listCustomer=new ListCustomer();
            listCustomer.generate_sample_dataset();
        }
        return listCustomer.getCustomers();
    }
    public ArrayList<Customer> get_customer_by_provider(String provider)
    {
        if(listCustomer==null)
        {
            listCustomer=new ListCustomer();
            listCustomer.generate_sample_dataset();
        }
        ArrayList<Customer> results=new ArrayList<>();
        for(Customer c: listCustomer.getCustomers())
        {
            if (c.getPhone().startsWith(provider)) {
                results.add(c);
            }
        }
        return results;
    }
    public boolean isExist(Customer c)
    {
        return listCustomer.isExist(c);
    }
    public void addCustomer(Customer c)
    {
        listCustomer.addCustomer(c);
    }
    //Code moi 04/06
    /**
     * Đây là hàm truy vấn toàn bộ dữ liệu khách hàng sau đó mô hình hóa thành hướng đối tượng
     * Hàm này trả về một đối tượng ListCustomer chứa danh sách khách hàng
     * @param database Cơ sở dữ liệu SQLite để truy vấn
     * @return ListCustomer
     */
    public ListCustomer getAllCustomers(SQLiteDatabase database)
    {
        listCustomer= new  ListCustomer();

        Cursor cursor = database.rawQuery("SELECT * FROM Customer", null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String email = cursor.getString(2);
            String phone = cursor.getString(3);
            String username = cursor.getString(4);
            String password = cursor.getString(5);

            Customer c = new Customer();
            c.setId(id);
            c.setName(name);
            c.setEmail(email);
            c.setPhone(phone);
            c.setUsername(username);
            c.setPassword(password);

            listCustomer.addCustomer(c);
        }
        cursor.close();
        return listCustomer;
    }

    // Hàm này dùng để thêm một khách hàng mới vào cơ sở dữ liệu SQLite
    public long saveNewCustomer(Customer c, SQLiteDatabase database) {
        ContentValues values = new ContentValues();
        values.put("Name", c.getName());
        values.put("Email", c.getEmail());
        values.put("Phone", c.getPhone());
        values.put("Username", c.getUsername());
        values.put("Password", c.getPassword());
        long flag=database.insert("Customer", null, values);
        return flag;
    }
public long saveUpdateCustomer(Customer c, SQLiteDatabase database) {
        ContentValues values = new ContentValues();
        values.put("Name", c.getName());
        values.put("Email", c.getEmail());
        values.put("Phone", c.getPhone());
        values.put("Username", c.getUsername());
        values.put("Password", c.getPassword());
        long flag=database.update("Customer", values, "Id=?", new String[]{String.valueOf(c.getId())});
        return flag;
    }

    public long removeCustomer(String id, SQLiteDatabase database) {
        int flag=database.delete("Customer", "Id=?", new String[]{String.valueOf(id)});
        return  flag;
    }
}
