package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_holder);
        btnAdd = findViewById(R.id.btnTaskAdd);
        TaskHolderDB tdb = new TaskHolderDB(TaskHolder.this);
        listViewTask = findViewById(R.id.listViewTask);
        taskData = tdb.getAll();

        tasks = new ArrayList<String>();
        for(TaskHolderData t: taskData){
            tasks.add(t.getTask());
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
                }else{
                    td = new TaskHolderData(0,task);
                    tdb.addOne(td);
                    tasks.add(task);
                    taskArr = new ArrayAdapter<String>(TaskHolder.this, android.R.layout.simple_list_item_1,tasks);
                    listViewTask.setAdapter(taskArr);

                }
            }
        });

    }

}