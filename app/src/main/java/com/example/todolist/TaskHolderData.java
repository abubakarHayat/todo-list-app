package com.example.todolist;

public class TaskHolderData {

    private String task;
    private int id;
    private String uname;

    public TaskHolderData(){}

    public TaskHolderData(int id,String task,String uname){
        this.id = id;
        this.task = task;
        this.uname = uname;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
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
