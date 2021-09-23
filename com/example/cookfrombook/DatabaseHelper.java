package com.example.cookfrombook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static  final String DATABASE_NAME = "RecipeUser.db";
    public static final String TABLE_NAME = "recipeuser_entry";
    public static final String col_1 = "FULL_NAME";
    public static final String col_2 = "EMAIL";
    public static final String col_3 = "PASSWORD";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + TABLE_NAME + "(FULL_NAME TEXT , EMAIL  TEXT primary key, PASSWORD TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);

    }

    public boolean insertdata(String full_name, String email, String password){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(col_1, full_name);
        contentValues.put(col_2, email);
        contentValues.put(col_3, password);

        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result!=-1)
            return true;
        else
            return false;

    }

    public boolean updatedata(String email, String password){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(col_3, password);

        long result = db.update(TABLE_NAME, contentValues, col_2 + " = ?", new String[] {email});

        if (result!=-1)
            return true;
        else
            return false;

    }

    public boolean isMailExist(String value){
        Log.i("Email", ""+value);
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + col_2 + " = ?";
        String[] whereArgs = {value};
        Cursor cursor = db.rawQuery(query, whereArgs);

        int count = cursor.getCount();
        Log.i("count", ""+count);

        if(count>0)
            return true;
        else
            return false;
    }

    public boolean checkusernamepass(String username, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + col_2 + " = ?" + " AND " + col_3 + " = ?";
        String[] whereArgs = {username, password};
        Cursor cursor = db.rawQuery(query, whereArgs);

        int count = cursor.getCount();

        if(count>0)
            return true;
        else
            return false;
    }

}
