package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btnLogin;
    Button btnSignup;
    EditText edtTxtUname;
    EditText edtTxtPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogin = findViewById(R.id.btnLogin);
        btnSignup = findViewById(R.id.btnSignup);


        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,SignupActivity.class);
                startActivity(i);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtTxtUname = findViewById(R.id.edtTxtName);
                edtTxtPass = findViewById(R.id.edtTxtPassword);
                String uName = edtTxtUname.getText().toString().trim();
                String pass = edtTxtPass.getText().toString().trim();
                if(uName.length() < 4 || pass.length() <4 ){
                    Toast.makeText(getApplicationContext(),
                            "Enter valid Username and Password", Toast.LENGTH_SHORT).show();
                }else{
                    Person p = new Person(0,uName,pass);
                    if(authUser(p)){
                        Intent i = new Intent(MainActivity.this,TaskHolder.class);
                        startActivity(i);

                    }
                }
            }
        });
    }

    public boolean authUser(Person person){
        PersonDB db = new PersonDB(MainActivity.this);
        List<Person> personList = new ArrayList<>();
        personList = db.getAll();
        for(Person p: personList){
            if(((String)p.getuName()).equals(person.getuName()) && ((String)p.getPassword()).equals(person.getPassword())){
                return true;
            }
        }
        return false;
    }
}