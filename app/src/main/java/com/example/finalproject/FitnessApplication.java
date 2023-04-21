package com.example.finalproject;

import android.annotation.SuppressLint;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class FitnessApplication extends android.app.Application {

    private static final String DB_NAME = "fitness_db";
    private static final int DB_VERSION = 1;
    private SQLiteOpenHelper helper;

    public void addResult(String name, int calories, String type){

        String fixedName = "'" + name + "'";

        String fixedType = "'" + type + "'";

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String fixedDate = "'" + LocalDateTime.now().format(dateTimeFormatter) + "'";

        Log.i("DBAdd", "Adding: " + fixedName + fixedDate + fixedType + calories);

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

    @SuppressLint("Range")
    public ArrayList<String> getNames() {
        ArrayList<String> names = new ArrayList<>();
        SQLiteDatabase db = helper.getReadableDatabase();
        try {
            Cursor cursor = db.rawQuery(" SELECT name FROM tbl_journal_entries", null);
            while (cursor.moveToNext()) {
                names.add(cursor.getString(cursor.getColumnIndex("name")));
            }
        } catch (Exception ex) {
            Log.e("DB", "Error getting names: " + ex.toString());
        }
        return names;
    }

    @SuppressLint("Range")
    public ArrayList<String> getCalories() {
        ArrayList<String> calories = new ArrayList<>();
        SQLiteDatabase db = helper.getReadableDatabase();
        try {
            Cursor cursor = db.rawQuery(" SELECT calories FROM tbl_journal_entries", null);
            while (cursor.moveToNext()) {
                calories.add(cursor.getString(cursor.getColumnIndex("calories")));
            }
        } catch (Exception ex) {
            Log.e("DB", "Error getting names: " + ex.toString());
        }
        return calories;
    }

    @SuppressLint("Range")
    public ArrayList<String> getTypes() {
        ArrayList<String> types = new ArrayList<>();
        SQLiteDatabase db = helper.getReadableDatabase();
        try {
            Cursor cursor = db.rawQuery(" SELECT type FROM tbl_journal_entries", null);
            while (cursor.moveToNext()) {
                types.add(cursor.getString(cursor.getColumnIndex("type")));
            }
        } catch (Exception ex) {
            Log.e("DB", "Error getting names: " + ex.toString());
        }
        return types;
    }

    @SuppressLint("Range")
    public ArrayList<String> getDates() {
        ArrayList<String> dates = new ArrayList<>();
        SQLiteDatabase db = helper.getReadableDatabase();
        try {
            Cursor cursor = db.rawQuery(" SELECT date FROM tbl_journal_entries", null);
            while (cursor.moveToNext()) {
                dates.add(cursor.getString(cursor.getColumnIndex("date")));
            }
        } catch (Exception ex) {
            Log.e("DB", "Error getting names: " + ex.toString());
        }
        return dates;
    }

    public double getCaloriesConsumed(){

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String fixedDate = "'" + LocalDateTime.now().format(dateTimeFormatter) + "'";

        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT SUM(calories) AS total_calories FROM tbl_journal_entries WHERE date = DATE('now') AND type = 'Food' ", null);
        cursor.moveToFirst();
        double caloriesConsumed = (int) cursor.getDouble(0);

        return caloriesConsumed;

    }

    public double getCaloriesBurned(){

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String fixedDate = "'" + LocalDateTime.now().format(dateTimeFormatter) + "'";

        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT SUM(calories) AS total_calories FROM tbl_journal_entries WHERE date = DATE('now') AND type = 'Activity' ", null);
        cursor.moveToFirst();
        double caloriesBurned = (int) cursor.getDouble(0);

        return caloriesBurned;

    }

}
