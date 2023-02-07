package com.cv.dosomethingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        Button aboutApp = findViewById(R.id.aboutApp_btn);
        Button aboutTeam = findViewById(R.id.aboutTeam_btn);
        setFragment(new AboutAppFragment());

        aboutApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setFragment(new AboutAppFragment());
            }
        });

        aboutTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setFragment(new AboutLocationFragment());
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.about_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        if(item.getItemId() == R.id.types_about_menu) {
            Intent toTypes = new Intent(AboutActivity.this, TypesActivity.class);
            startActivity(toTypes);
        }else if(item.getItemId() == R.id.home_about_menu){
            Intent toHome = new Intent(AboutActivity.this, MainActivity.class);
            startActivity(toHome);
        }else if(item.getItemId() == R.id.logout_about_menu){
            Intent toLogout = new Intent(AboutActivity.this, LoginActivity.class);
            startActivity(toLogout);
        }else if(item.getItemId() == R.id.saved_about_menu){
            Intent toSaved = new Intent(AboutActivity.this, SavedActivity.class);
            startActivity(toSaved);
        }
        return true;
    }

    private void setFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.about_frameLayout, fragment);
        fragmentTransaction.commit();
    }
}