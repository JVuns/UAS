package com.cv.cgpcinema;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private SmsManager smsManager;
    private int sendSmsPermission;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBConnect db = DBConnect.getInstance(this);

        Retrofit retrofitMovie = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/authentication/token/new?api_key=")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MovieAPIInterface movieApi = retrofitMovie.create(MovieAPIInterface.class);

        Call<Activity> req = movieApi.getActivityAction();

        req.enqueue(new Callback<Activity>() {
            @Override
            public void onResponse(Call<Activity> call, Response<Activity> response) {
                if (!response.isSuccessful()) {
                    return;
                }

            }

            @Override
            public void onFailure(Call<Activity> call, Throwable t) {

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.app_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        if(item.getItemId() == R.id.about_menu){
            Intent toAbout = new Intent(MainActivity.this, Location.class);
            startActivity(toAbout);
        }else if(item.getItemId() == R.id.saved_menu){
        }
        return true;
    }
}