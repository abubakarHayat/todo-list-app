package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

public class TaskHolder extends AppCompatActivity {

    Button btnAdd;
    ListView listViewTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_holder);
    }
}