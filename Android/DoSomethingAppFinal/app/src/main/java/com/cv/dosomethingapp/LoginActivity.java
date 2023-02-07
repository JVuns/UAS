package com.cv.dosomethingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        DBConnect db = DBConnect.getInstance(this);
        Button login = findViewById(R.id.login_btn);
        Button redirectRegister = findViewById(R.id.redirectRegister_btn);
        EditText email = findViewById(R.id.emailLogin_editText);
        EditText password = findViewById(R.id.passwordLogin_editText);

        redirectRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toRegister = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(toRegister);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strEmail = email.getText().toString();
                String strPassword = password.getText().toString();

                if(db.checkEmailPassword(strEmail, strPassword)){
                    db.getUser(strEmail);
                    System.out.println("[DEBUG JEV]: " + GlobalStorage.user.getUsername());
                    Intent toHome = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(toHome);
                }else{
                    Toast.makeText(LoginActivity.this, "Email or Password is wrong", Toast.LENGTH_LONG).show();
                    return;
                }
            }
        });

    }
}