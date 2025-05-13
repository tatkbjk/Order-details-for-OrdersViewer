package com.example.k22411csampleproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.utils.BMIResult;
import com.utils.HealthCare;

public class EmployeeHealthcareActivity extends AppCompatActivity {
    EditText edtHeight, edtWeight;
    Button btnCalculate, btnClear, btnClose;
    TextView txtResult;

    View.OnClickListener myClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_employee_healthcare);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        addViews();
        addEvents();

    }

    private void addEvents() {
        myClick = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view.equals(btnCalculate))
                {
                    //View calculate dang su dung su kien nay
                    double h=Double.parseDouble(edtHeight.getText().toString());
                    double w=Double.parseDouble(edtWeight.getText().toString());
                    BMIResult result= HealthCare.calculate(h,w,EmployeeHealthcareActivity.this);
                    txtResult.setText(result.getBmi()+""+result.getDescription());
                }
                else if(view.equals(btnClear))
                {
                    edtHeight.setText("");
                    edtWeight.setText("");
                    txtResult.setText("");
                    edtHeight.requestFocus();
                    //View clear dang su dung su kien nay
                }
                else if(view.equals(btnClose))
                {
                    finish();
                //View close dang su dung su kien nay
                }
                //Kiểm tra các View sử dụng biến sự kiện

            }
        }; //Gán sự kiện cho các View (sharing events)
        btnCalculate.setOnClickListener(myClick);
        btnClear.setOnClickListener(myClick);
        btnClose.setOnClickListener(myClick);
    }
    private void addViews() {
        edtHeight = findViewById(R.id.edtHeight);
        edtWeight = findViewById(R.id.edtWeight);

        btnCalculate = findViewById(R.id.btnCalculate);
        btnClear = findViewById(R.id.btnClear);
        btnClose = findViewById(R.id.txtClose);

        txtResult = findViewById(R.id.txtResult);

    }


}