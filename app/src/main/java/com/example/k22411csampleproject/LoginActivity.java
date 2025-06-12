package com.example.k22411csampleproject;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.connectors.EmployeeConnector;
import com.connectors.SQLiteConnector;
import com.thaianhthu.models.Employee;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class LoginActivity extends AppCompatActivity {

    EditText edtUsername;
    EditText edtPassword;
    CheckBox chkSaveLoginInfor;
    private boolean doubleBackToExitPressedOnce = false;
    private static final long DOUBLE_BACK_PRESS_THRESHOLD = 1200;

    //Cop 3 dòng này ở 25,26,27 file MainActivity.java (thầy đưa)
    String DATABASE_NAME="SalesDatabase.sqlite";
    private static final String DB_PATH_SUFFIX = "/databases/";
    SQLiteDatabase database=null;
//    Button btnLogin;
//    ImageView imgExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        addViews();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //Cop ở dòng 34 file MainActivity.java (thầy đưa)
        processCopy();
    }

    private void addViews() {
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        chkSaveLoginInfor = findViewById(R.id.chkSaveLoginInfor);
//        btnLogin = findViewById(R.id.btnLogin);
//        imgExit = findViewById(R.id.imgExit);
    }

    public void do_login(View view) {
        String username = edtUsername.getText().toString();
        String password = edtPassword.getText().toString();
//        boolean saveinfor = chkSaveLoginInfor.isChecked();

        EmployeeConnector ec=new EmployeeConnector();

        SQLiteConnector sqLiteConnector = new SQLiteConnector(this);
        sqLiteConnector.openDatabase();
//        Employee emp = ec.login(sqLiteConnector.getDatabase(), username, password);
        Employee emp = ec.login(new SQLiteConnector(this).openDatabase(), username, password);

        if (emp!=null)
        {
            Intent intent = new Intent(this, MainActivity.class);
            //(màn hình hiện tại, màn hình đích)
            startActivity(intent);
        }
        else
        {
            Toast.makeText(this, "Login failed, please check your account again!", Toast.LENGTH_SHORT).show();
        }

    }

    public void do_exit(View view) {
        AlertDialog.Builder builder=new AlertDialog.Builder(LoginActivity.this);
        Resources res=getResources();
        //Thiet lap tieu de
        builder.setTitle(res.getText(R.string.confirm_exit_title));
        //Noi dung cua so:
        builder.setMessage(res.getText(R.string.confirm_exit_message));
        //Bieu tuong
        builder.setIcon(android.R.drawable.ic_dialog_alert);

        //Thiet lap tuong tac YES
        builder.setPositiveButton(res.getText(R.string.confirm_exit_yes), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//                System.exit(0);
                finish();
            }
        });
        builder.setNegativeButton(res.getText(R.string.confirm_exit_no), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog dialog=builder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (doubleBackToExitPressedOnce) {
                finish();
                return true;
            }

            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, DOUBLE_BACK_PRESS_THRESHOLD);

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void saveLoginInformation()
    {
        //Luu thong tin dang nhap vao SharedPreferences
        SharedPreferences preferences=getSharedPreferences("LOGIN_INFORMATION", MODE_PRIVATE);
        SharedPreferences.Editor editor =preferences.edit();
        String usr=edtUsername.getText().toString();
        String pwd=edtPassword.getText().toString();
        boolean isSave=chkSaveLoginInfor.isChecked();
        editor.putString("USERNAME",usr);
        editor.putString("PASSWORD",pwd);
        editor.putBoolean("SAVED",isSave);
        editor.commit();
    }

    //Chỉ cần gõ onPause() là được, lưu xuống onPause để tránh mất dữ liệu khi thoát ứng dụng
    @Override
    protected void onPause() {
        super.onPause();
        saveLoginInformation();
    }

    public void restoreLoginInformation()
    {
        SharedPreferences preferences=getSharedPreferences("LOGIN_INFORMATION", MODE_PRIVATE);
        //Lấy thông tin đã lưu, nếu không tìm thấy thì trả về chuỗi rỗng
        String usr=preferences.getString("USERNAME","");
        String pwd=preferences.getString("PASSWORD","");
        boolean isSave=preferences.getBoolean("SAVED",true);
        if(isSave)
        {
            edtUsername.setText(usr);
            edtPassword.setText(pwd);
            chkSaveLoginInfor.setChecked(true);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        restoreLoginInformation();
    }
    //Cop 3 hàm này từ dòng 37 file MainActivity.java (thầy đưa)
    private void processCopy() {
        //private app
        File dbFile = getDatabasePath(DATABASE_NAME);

        if (!dbFile.exists())
        {
            try
            {
                CopyDataBaseFromAsset();
                Toast.makeText(this, "Copying sucess from Assets folder", Toast.LENGTH_LONG).show();
            }
            catch (Exception e)
            {
                Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
            }
        }
    }
    private String getDatabasePath() {
        return getApplicationInfo().dataDir + DB_PATH_SUFFIX+ DATABASE_NAME;
    }
    public void CopyDataBaseFromAsset()
    {
        try {
            InputStream myInput;

            myInput = getAssets().open(DATABASE_NAME);


            // Path to the just created empty db
            String outFileName = getDatabasePath();

            // if the path doesn't exist first, create it
            File f = new File(getApplicationInfo().dataDir + DB_PATH_SUFFIX);
            if (!f.exists())
                f.mkdir();

            // Open the empty db as the output stream
            OutputStream myOutput = new FileOutputStream(outFileName);

            // transfer bytes from the inputfile to the outputfile
            byte[] buffer = new byte[1024];
            int length;
            while ((length = myInput.read(buffer)) > 0) {
                myOutput.write(buffer, 0, length);
            }

            // Close the streams
            myOutput.flush();
            myOutput.close();
            myInput.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
