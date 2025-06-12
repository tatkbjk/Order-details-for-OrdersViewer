package com.thaianhthu.models;

import java.util.ArrayList;

public class ListPaymentMethod {
    ArrayList<PaymentMethod> paymentMethods;
    public ListPaymentMethod()
    {
        paymentMethods = new ArrayList<>();
    }

    public ArrayList<PaymentMethod> getPaymentMethods() {
        return paymentMethods;
    }

    public void setPaymentMethods(ArrayList<PaymentMethod> paymentMethods) {
        this.paymentMethods = paymentMethods;
    }

    // Xóa hoặc comment hàm sinh dữ liệu mẫu để chỉ lấy dữ liệu từ SQLite
    // public void gen_payment_methods()
    // {
    //     paymentMethods.add(new PaymentMethod(1, "INTERNET BANKING", "Thanh toán bằng tài khoản ngân hàng"));
    //     paymentMethods.add(new PaymentMethod(2, "COD", "Thanh toán khi nhận hàng"));
    //     paymentMethods.add(new PaymentMethod(3, "MOMO", "Thanh toán bằng ví điện tử Momo"));
    //     paymentMethods.add(new PaymentMethod(4, "CASH", "Thanh toán bằng tiền mặt tại quầy"));
    // }
}
