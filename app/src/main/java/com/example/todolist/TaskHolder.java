package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class TaskHolder extends AppCompatActivity {

    Button btnAdd;
    EditText edtTxtTask;
    ListView listViewTask;
    ArrayAdapter taskArr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_holder);
        TaskHolderDB tdb = new TaskHolderDB(TaskHolder.this);

        taskArr = new ArrayAdapter<TaskHolderData>(TaskHolder.this, android.R.layout.simple_list_item_1
        ,tdb.getAll());
        listViewTask.setAdapter(taskArr);
        btnAdd = findViewById(R.id.btnTaskAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TaskHolderData td;
                edtTxtTask = findViewById(R.id.edtTxtTask);
                String task = edtTxtTask.toString().trim();
                if(task.length() == 0 ){
                    Toast.makeText(getApplicationContext(),"Please enter a valid task!",Toast.LENGTH_SHORT).show();
                }else{
                    td = new TaskHolderData(0,task);
                    tdb.addOne(td);
                    taskArr = new ArrayAdapter<TaskHolderData>(TaskHolder.this, android.R.layout.simple_list_item_1
                            ,tdb.getAll());
                    listViewTask.setAdapter(taskArr);

                }
            }
        });

    }
}