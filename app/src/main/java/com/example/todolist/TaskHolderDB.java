package com.example.todolist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class TaskHolderDB extends SQLiteOpenHelper {

    public TaskHolderDB(Context context){

        super(context,"taskholder",null,1 );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE taskholder ( ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                " task TEXT NOT NULL)";

        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + "taskholder");
        onCreate(db);
    }

    public long addOne(TaskHolderData tk){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("username", tk.getTask());
        long result = db.insert("taskholder", null,cv);
        db.close();
        return result;
    }
    public boolean deleteOne(TaskHolderData tk){
        SQLiteDatabase db = this.getWritableDatabase();
        String deleteRecord = "DELETE FROM taskholder WHERE id =" + tk.getId();
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
    public List<TaskHolderData> getAll(){
        List<TaskHolderData> retList = new ArrayList<>();
        String readData = "SELECT * FROM taskholder";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(readData,null);
        if(cursor.moveToFirst()){
            do{
                int id = cursor.getInt(0);
                String taskText = cursor.getString(1);
                TaskHolderData td = new TaskHolderData(id,taskText);
                retList.add(td);

            }while(cursor.moveToNext());
        }else{
            //empty list
        }
        cursor.close();
        db.close();
        return retList;

    }
}
