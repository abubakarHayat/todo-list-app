package com.example.todolist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class TaskHolderDB extends SQLiteOpenHelper {

    public TaskHolderDB(Context context){

        super(context,TaskDataUtil.DATABASE_NAME,null,1 );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TaskDataUtil.TABLE_NAME  +
                " ( " + TaskDataUtil.COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TaskDataUtil.COL_TASK + " TEXT NOT NULL, " + TaskDataUtil.COL_UNAME +" TEXT NOT NULL )";

        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TaskDataUtil.TABLE_NAME);
        onCreate(db);
    }

    public boolean addOne(TaskHolderData tk){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TaskDataUtil.COL_TASK, tk.getTask());
        cv.put(TaskDataUtil.COL_UNAME,tk.getUname());
        long result = db.insert(TaskDataUtil.TABLE_NAME, null,cv);
        db.close();
        return (result == -1) ? false:true;
    }
    public boolean deleteOne(String str){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TaskDataUtil.TABLE_NAME, TaskDataUtil.COL_TASK+"=?",new String[]{str});
        db.close();
        return true;
    }
    public List<TaskHolderData> getAll(String uname){
        List<TaskHolderData> retList = new ArrayList<>();
        /*String readData = "SELECT * FROM " + TaskDataUtil.TABLE_NAME + " WHERE " +
                TaskDataUtil.COL_UNAME + " = %" + uname+"%";*/
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TaskDataUtil.TABLE_NAME,
                new String[]{TaskDataUtil.COL_ID,TaskDataUtil.COL_TASK,TaskDataUtil.COL_UNAME},
                TaskDataUtil.COL_UNAME + " LIKE ?",
                new String[]{"%"+uname+"%"},null,null,null);
         //Cursor cursor = db.rawQuery(readData,null);
        if(cursor.moveToFirst()){
            Log.d("getAll", "Data");
            do{
                int id = cursor.getInt(0);
                String taskText = cursor.getString(1);
                String user = cursor.getString(2);
                TaskHolderData td = new TaskHolderData(id,taskText,user);
                retList.add(td);

            }while(cursor.moveToNext());
        }else{
            //empty list
            Log.d("getAll", "NO Data");
        }
        cursor.close();
        db.close();
        return retList;

    }
    public boolean isSame(String str){

        String readData = "SELECT * FROM " + TaskDataUtil.TABLE_NAME  +
                " WHERE "  + TaskDataUtil.COL_TASK + " = " +  str;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(readData,null);
        if(cursor != null){
            cursor.close();
            db.close();
            return true;

        }else{
            cursor.close();
            db.close();
            return false;
        }


    }
}
