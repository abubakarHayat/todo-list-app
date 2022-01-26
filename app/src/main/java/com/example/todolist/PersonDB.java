package com.example.todolist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class PersonDB extends SQLiteOpenHelper {

    public PersonDB(Context context){
        super(context,"person.db",null,1 );
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

    public long addOne(Person person){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("username", person.getuName());
        cv.put("password", person.getPassword());
        long result = db.insert("person.db", null,cv);
        db.close();
        return result;
    }

    public boolean deleteOne(Person p){
        SQLiteDatabase db = this.getWritableDatabase();
        String deleteRecord = "DELETE FROM person.db WHERE id =" + p.getId();
        Cursor cursor = db.rawQuery(deleteRecord,null);
        if(cursor.moveToFirst()){
            cursor.close();
            db.close();
            return true;
        }
        cursor.close();
        db.close();
        return false;

    }
    public List<Person> getAll(){
        List<Person> retList = new ArrayList<>();
        String readData = "SELECT * FROM person.db";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(readData,null);
        if(cursor.moveToFirst()){
            do{
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String pass = cursor.getString(2);
                Person p = new Person(id,name,pass);
                retList.add(p);

            }while(cursor.moveToNext());
        }else{
            //empty list
        }
        cursor.close();
        db.close();
        return retList;

    }
}
