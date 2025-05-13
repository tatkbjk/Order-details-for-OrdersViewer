package com.utils;

import android.app.Activity;

import com.example.k22411csampleproject.R;

public class HealthCare {
    public static BMIResult calculate(double height, double weight, Activity context) {
        double bmi = weight / (height * height);
        BMIResult result = new BMIResult();
        result.setBmi(bmi);
        String des="";
        if (bmi < 18.5)
            des=context.getResources().getString(R.string.title_slim);
        else if (bmi < 25)
            des=context.getResources().getString(R.string.title_normal);
        else
            des=context.getResources().getString(R.string.title_fat);
        result.setDescription(des);
        return result;}
}
