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
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SavedActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    Adapterdata adapterdata;
    List<Activity> activityList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved);
        TextView emailText = findViewById(R.id.email_text);
        TextView nameText = findViewById(R.id.name_text);

        recyclerView = findViewById(R.id.activity_recycle_view);
        DBConnect db = DBConnect.getInstance(this);
        ArrayList<Activity> listData = db.getActivity();

        linearLayoutManager = new LinearLayoutManager(this, linearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapterdata = new Adapterdata(this, listData);
        recyclerView.setAdapter(adapterdata);
        adapterdata.notifyDataSetChanged();

        emailText.setText("Email: " + GlobalStorage.user.getEmail());
        nameText.setText("Name: " + GlobalStorage.user.getUsername());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.saved_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        if(item.getItemId() == R.id.types_saved_menu) {
            Intent toTypes = new Intent(SavedActivity.this, TypesActivity.class);
            startActivity(toTypes);
        }else if(item.getItemId() == R.id.about_saved_menu){
            Intent toAbout = new Intent(SavedActivity.this, AboutActivity.class);
            startActivity(toAbout);
        }else if(item.getItemId() == R.id.logout_saved_menu){
            Intent toLogout = new Intent(SavedActivity.this, LoginActivity.class);
            startActivity(toLogout);
        }else if(item.getItemId() == R.id.home_saved_menu){
            Intent toHome = new Intent(SavedActivity.this, MainActivity.class);
            startActivity(toHome);
        }
        return true;
    }
}