package com.example.todolist;

public class TaskHolderData {

    private String task;
    private int id;

    public TaskHolderData(){}

    public TaskHolderData(String task){
        this.task = task;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public int getId() {
        return id;
    }

    public void setTask(int id) {
        this.id = id;
    }
}
