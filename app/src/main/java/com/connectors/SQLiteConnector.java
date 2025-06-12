package com.connectors;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class SQLiteConnector {
    String DATABASE_NAME="SalesDatabase.sqlite";
    private static final String DB_PATH_SUFFIX = "/databases/";
    SQLiteDatabase database=null;
    Activity context;

    public SQLiteConnector() {
    }
    public SQLiteConnector(Activity context) {
        this.context = context;
    }
// Gen getters and Setters cho context thôi
    public Activity getContext() {
        return context;
    }

    public void setContext(Activity context) {
        this.context = context;
    }
    // Hàm này mở database, nếu chưa có thì sẽ tạo mới
    public SQLiteDatabase openDatabase() {
        database = this.context.openOrCreateDatabase(DATABASE_NAME, Context.MODE_PRIVATE, null);
        return database;
    }
    // Getter và Setter cho database
    public SQLiteDatabase getDatabase() {
        return database;
    }

    public void setDatabase(SQLiteDatabase database) {
        this.database = database;
    }
}
