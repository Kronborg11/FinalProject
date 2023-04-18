package com.example.finalproject;

import android.app.Application;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FitnessApplication extends android.app.Application {

    private static final String DB_NAME = "fitness_db";
    private static final int DB_VERSION = 1;
    private SQLiteOpenHelper helper;

    public void addResult(String name, int calories, String type){

        String fixedName = "'" + name + "'";

        String fixedType = "'" + type + "'";

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String fixedDate = "'" + LocalDateTime.now().format(dateTimeFormatter) + "'";

        SQLiteDatabase db = helper.getWritableDatabase();

        db.execSQL("INSERT INTO tbl_journal_entries (name, calories, type, date) VALUES ("
                + fixedName + "," + calories + "," + fixedType + "," + fixedDate + ")");

    }

    public void reset(){

        SQLiteDatabase database = helper.getWritableDatabase();
        database.execSQL("DELETE FROM tbl_journal_entries;");

    }

    @Override
    public void onCreate() {
        super.onCreate();

        helper = new SQLiteOpenHelper(this, DB_NAME, null, DB_VERSION) {
            @Override
            public void onCreate(SQLiteDatabase db) {
                db.execSQL("CREATE TABLE IF NOT EXISTS tbl_journal_entries (" +
                        "name TEXT," +
                        "calories INTEGER," +
                        "type TEXT," +
                        "date TEXT" +
                        ")");
            }

            @Override
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {  }
        };

    }

}
