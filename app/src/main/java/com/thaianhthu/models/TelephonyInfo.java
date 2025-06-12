package com.thaianhthu.models;

import androidx.annotation.NonNull;

public class TelephonyInfo {
    private String name;
    private String phone;

    public TelephonyInfo() {
    }
    public TelephonyInfo(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @NonNull
    @Override
    public String toString() {
        return this.name + "\n" + this.phone;
    }
}
