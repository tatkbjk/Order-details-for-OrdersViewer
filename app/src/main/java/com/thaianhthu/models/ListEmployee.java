package com.thaianhthu.models;

import java.util.ArrayList;

public class ListEmployee {
    private ArrayList<Employee> employees;

    public ListEmployee() {
        employees=new ArrayList<>();
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(ArrayList<Employee> employees) {
        this.employees = employees;
    }
    public void gen_dataset(){
        Employee e1=new Employee();
        e1.setId(1);
        e1.setName("Thanh Duy");
        e1.setEmail("delilah@thealbum.com");
        e1.setPhone("0123456789");
        e1.setUsername("delilah");
        e1.setPassword("1331");
        employees.add(e1);

        Employee e2=new Employee();
        e2.setId(2);
        e2.setName("Duy Khánh Zhou Zhou");
        e2.setEmail("nhdk@example.com");
        e2.setPhone("0123456789");
        e2.setUsername("dkzhou");
        e2.setPassword("1331");
        employees.add(e2);

        Employee e3=new Employee();
        e3.setId(3);
        e3.setName("Bùi Công Nam");
        e3.setEmail("bcn@example.com");
        e3.setPhone("0123456789");
        e3.setUsername("bcn");
        e3.setPassword("1331");
        employees.add(e3);

        Employee e4=new Employee();
        e4.setId(4);
        e4.setName("Thiên Minh");
        e4.setEmail("thichtichcuc@vtm.com");
        e4.setPhone("0123456789");
        e4.setUsername("vbt");
        e4.setPassword("1331");

    }
}

