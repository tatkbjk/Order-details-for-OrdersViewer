package com.example.k22411csampleproject;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.thaianhthu.models.TelephonyInfo;

public class TelephonyActivity extends AppCompatActivity {
    ListView lvTelephonyInfo;
    ArrayAdapter<TelephonyInfo> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_telephony);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        addViews();
        getAllContacts();
        addEvents();
    }

    private void addEvents() {
        lvTelephonyInfo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                TelephonyInfo ti = adapter.getItem(i);
                makeAPhoneCall(ti);
            }
        });
    }

    private void makeAPhoneCall(TelephonyInfo ti) {
        Uri uri = Uri.parse("tel:" + ti.getPhone());
        Intent intent = new Intent(Intent.ACTION_DIAL, uri);
        intent.setData(uri);
        startActivity(intent);
    }

    private void getAllContacts() {
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        Cursor cursor = getContentResolver().query(uri, null,null, null, null);

        while(cursor.moveToNext()){
            int nameIndex = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
            String name = cursor.getString(nameIndex);
            int phoneIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
            String phone = cursor.getString(phoneIndex);

            TelephonyInfo ti=new TelephonyInfo();
            ti.setName(name);
            ti.setPhone(phone);
            adapter.add(ti);
        }
        cursor.close();
    }

    private void addViews() {
        lvTelephonyInfo=findViewById(R.id.lvTelephonyInfo);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        lvTelephonyInfo.setAdapter(adapter);
    }

}