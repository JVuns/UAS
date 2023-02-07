package com.cv.dosomethingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;

public class TypesActivity extends AppCompatActivity {

    RecyclerView types;
    ArrayList<ActivityTypes> typesList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_types);

        types = findViewById(R.id.types_recyclerView);
        typesList.addAll(ActivityTypesList.getListData());
        types.setLayoutManager(new LinearLayoutManager(this));
        ActivityTypesAdapter adapter = new ActivityTypesAdapter(typesList);
        types.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.types_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        if(item.getItemId() == R.id.about_types_menu) {
            Intent toAbout = new Intent(TypesActivity.this, AboutActivity.class);
            startActivity(toAbout);
        }else if(item.getItemId() == R.id.home_types_menu){
            Intent toHome = new Intent(TypesActivity.this, MainActivity.class);
            startActivity(toHome);
        }else if(item.getItemId() == R.id.logout_types_menu){
            Intent toLogout = new Intent(TypesActivity.this, LoginActivity.class);
            startActivity(toLogout);
        }else if(item.getItemId() == R.id.saved_types_menu){
            Intent toSaved = new Intent(TypesActivity.this, SavedActivity.class);
            startActivity(toSaved);
        }
        return true;
    }
}