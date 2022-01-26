package com.example.todolist;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PersonDB extends SQLiteOpenHelper {

    public PersonDB(Context context,String table){
        super(context,table,null,1 );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE person.db ( ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                " username TEXT NOT NULL, password TEXT NOT NULL)";

        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + "person.db");
        onCreate(db);
    }

    public void addOne(Person person){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("username", person.getuName());
        cv.put("password", person.getPassword());
        db.insert("person.db", null,cv);
        db.close();
    }
}
