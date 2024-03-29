package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {

    Button btnBack;
    Button btnSignup;
    EditText edtTxtUnameS;
    EditText edtTxtPassS;
    PersonDB db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        btnBack = findViewById(R.id.btnBackS);
        btnSignup = findViewById(R.id.btnSignupS);
        db = new PersonDB(SignupActivity.this);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtTxtUnameS = findViewById(R.id.edtTxtNameS);
                edtTxtPassS = findViewById(R.id.edtTxtPasswordS);
                String uName = edtTxtUnameS.getText().toString().trim();
                String pass = edtTxtPassS.getText().toString().trim();
                if(uName.length() < 4 || pass.length() < 4 ){
                    Toast.makeText(getApplicationContext(),
                            "Enter valid Username and Password", Toast.LENGTH_SHORT).show();
                }else if(db.isSame(uName)){
                    Toast.makeText(getApplicationContext(),
                            "Username already exists!", Toast.LENGTH_SHORT).show();
                }
                else{
                    Person p = new Person(0,uName,pass);
                    db.addOne(p);
                    Toast.makeText(getApplicationContext(),"Signed up successfully!",Toast.LENGTH_SHORT).show();
                    finish();

                }
            }
        });
    }
}