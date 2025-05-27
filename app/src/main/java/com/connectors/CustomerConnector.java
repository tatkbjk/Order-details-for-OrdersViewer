package com.connectors;

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

}
