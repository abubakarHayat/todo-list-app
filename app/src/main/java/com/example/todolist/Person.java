package com.example.todolist;

public class Person {

    private int  id;
    private String uName;
    private String password;

    public Person(){}

    public Person(int id, String uName,String password){
        this.id = id;
        this.uName = uName;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
