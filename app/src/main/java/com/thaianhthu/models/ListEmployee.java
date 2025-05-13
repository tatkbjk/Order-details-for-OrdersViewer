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
        e1.setName("john");
        e1.setEmail("john.mclean@examplepetstore.com");
        e1.setPhone("0123456789");
        e1.setUsername("john123");
        e1.setPassword("123456");
        employees.add(e1);

        Employee e2=new Employee();
        e2.setId(2);
        e2.setName("jane");
        e2.setEmail("jane@example.com");
        e2.setPhone("0123456789");
        e2.setUsername("jane123");
        e2.setPassword("123456");
        employees.add(e2);

        Employee e3=new Employee();
        e3.setId(3);
        e3.setName("jack");
        e3.setEmail("jack@fireflies.com");
        e3.setPhone("0123456789");
        e3.setUsername("j97");
        e3.setPassword("123456");
        employees.add(e3);
    }
}

