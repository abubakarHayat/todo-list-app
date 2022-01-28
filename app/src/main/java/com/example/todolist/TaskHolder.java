package com.example.todolist;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class TaskHolder extends AppCompatActivity {

    Button btnAdd;
    EditText edtTxtTask;
    ListView listViewTask;
    ArrayAdapter taskArr;
    List<TaskHolderData> taskData;
    List<String> tasks;
    String user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_holder);
        btnAdd = findViewById(R.id.btnTaskAdd);
        listViewTask = findViewById(R.id.listViewTask);
        user = getIntent().getStringExtra("user");
        TaskHolderDB tdb = new TaskHolderDB(TaskHolder.this);
        taskData = tdb.getAll(user);
        if(taskData.size()==0)
            Log.d("GRASS", "ss");
        tasks = new ArrayList<String>();
        for(TaskHolderData t: taskData){
            tasks.add(t.getTask());
            Log.d("GRASS", "ss");
        }
        taskArr = new ArrayAdapter<String>(TaskHolder.this, android.R.layout.simple_list_item_1
                ,tasks);
            listViewTask.setAdapter(taskArr);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TaskHolderData td;
                edtTxtTask = findViewById(R.id.edtTxtTask);
                String task = edtTxtTask.getText().toString().trim();
                if(task.length() == 0 ){
                    Toast.makeText(getApplicationContext(),"Please enter a valid task!",Toast.LENGTH_SHORT).show();
                }/*else if(tdb.isSame(task)){
                    Toast.makeText(getApplicationContext(),"Same task cannot be repeated!",Toast.LENGTH_SHORT).show();
                }*/
                else{
                    td = new TaskHolderData(0,task,user);
                    tdb.addOne(td);
                    tasks.add(task);
                    taskArr = new ArrayAdapter<String>(TaskHolder.this, android.R.layout.simple_list_item_1,tasks);
                    listViewTask.setAdapter(taskArr);
                }
            }
        });

        listViewTask.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                final int item = i;
                new AlertDialog.Builder(TaskHolder.this)
                        .setIcon(android.R.drawable.ic_delete)
                        .setTitle("Task Completed?")
                        .setMessage("Is this task completed and to be removed?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if(tdb.deleteOne(tasks.get(item))){
                                    Toast.makeText(getApplicationContext(),"Task deleted!", Toast.LENGTH_SHORT)
                                    .show();
                                }
                                Log.d("DELETE STRING = ", tasks.get(item));
                                tasks.remove(item);
                                taskArr.notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("No",null)
                        .show();
                return true;
            }
        });

    }

}