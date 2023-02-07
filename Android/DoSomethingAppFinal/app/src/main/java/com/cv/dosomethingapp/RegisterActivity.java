package com.cv.dosomethingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        DBConnect db = DBConnect.getInstance(this);
        Button register = findViewById(R.id.register_btn);
        Button redirectLogin = findViewById(R.id.redirectLogin_btn);
        EditText name = findViewById(R.id.name_editText);
        EditText email = findViewById(R.id.email_editText);
        EditText password = findViewById(R.id.password_editText);
        EditText confirmPassword = findViewById(R.id.confirmPassword_editText);

        redirectLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toLogin = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(toLogin);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(name.getText().toString().length() <= 1){
                    Toast.makeText(RegisterActivity.this, "Name must have 2 or more characters", Toast.LENGTH_LONG).show();
                    return;
                }
                if(!email.getText().toString().contains("@")){
                    Toast.makeText(RegisterActivity.this, "Email must be valid", Toast.LENGTH_LONG).show();
                    return;
                }
                if(password.getText().toString().length() <= 4){
                    Toast.makeText(RegisterActivity.this, "Password must have 5 or more characters", Toast.LENGTH_LONG).show();
                    return;
                }
                if(!password.getText().toString().equals(confirmPassword.getText().toString())){
                    Toast.makeText(RegisterActivity.this, "Password and confirm password must match", Toast.LENGTH_LONG).show();
                    return;
                }

                System.err.println("This error is visible");

                Boolean checkUser = db.checkUsername(name.getText().toString());

                if(checkUser){
                    Toast.makeText(RegisterActivity.this, "Username already exist", Toast.LENGTH_LONG).show();
                    return;
                }

                Boolean checkEmail = db.checkEmail(email.getText().toString());

                if(checkEmail){
                    Toast.makeText(RegisterActivity.this, "Email is already registered", Toast.LENGTH_LONG).show();
                    return;
                }

                db.insertData(name.getText().toString(), email.getText().toString(), password.getText().toString());
                Intent toHome = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(toHome);
            }
        });

    }
}