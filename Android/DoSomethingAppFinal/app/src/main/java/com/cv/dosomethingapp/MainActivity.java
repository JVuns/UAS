package com.cv.dosomethingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Telephony;
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
        TextView activityText = findViewById(R.id.activity_text);
        TextView activityTypeExplanation = findViewById(R.id.activity_type_explanation_text);
        TextView activityTypeText = findViewById(R.id.activity_type_text);
        TextView activityParticipantsExplanation = findViewById(R.id.activity_participants_explanation_text);
        TextView activityParticipantsText = findViewById(R.id.activity_participants_text);
        Button getActivity = findViewById(R.id.getActivity_btn);
        Button saveActivity = findViewById(R.id.saveActivity_btn);
        Button smsActivity = findViewById(R.id.smsActivity_btn);

        getActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://www.boredapi.com/api/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                ActivityAPIInterface api = retrofit.create(ActivityAPIInterface.class);

                Call<Activity> call = api.getActivityAction();

                call.enqueue(new Callback<Activity>() {
                    @Override
                    public void onResponse(Call<Activity> call, Response<Activity> response) {
                        if (!response.isSuccessful()) {
                            activityText.setText("Code: " + response.code());
                            return;
                        }

                        Activity activity = response.body();
                        String name = activity.getActivity().toUpperCase();
                        activityText.setVisibility(View.VISIBLE);
                        activityText.setText(name);
                        String type = activity.getType().toUpperCase();
                        activityTypeExplanation.setVisibility(View.VISIBLE);
                        activityTypeText.setVisibility(View.VISIBLE);
                        activityTypeText.setText(type);
                        int participants = activity.getParticipants();
                        activityParticipantsExplanation.setVisibility(View.VISIBLE);
                        activityParticipantsText.setVisibility(View.VISIBLE);
                        saveActivity.setVisibility(View.VISIBLE);
                        smsActivity.setVisibility(View.VISIBLE);
                        if(participants > 1){
                            activityParticipantsText.setText(participants + " people preferably \n Try to invite your friends first!");

                        }else if(participants == 1){
                            activityParticipantsText.setText(participants + " person only \n You can do it by yourself!");
                        }

                    }

                    @Override
                    public void onFailure(Call<Activity> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
            }
        });

        saveActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Activity saved!", Toast.LENGTH_LONG).show();
                db.insertActivity(GlobalStorage.user.getId(), activityText.getText().toString(), activityTypeText.getText().toString(), activityParticipantsText.getText().toString());
            }
        });

        smsActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                smsManager = smsManager.getDefault();
                String message = activityText.getText().toString() + " " + activityTypeText.getText().toString() + " " + activityParticipantsText.getText().toString();
                smsManager.sendTextMessage("5554", null, message, null, null);
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
        if(item.getItemId() == R.id.types_menu) {
            Intent toTypes = new Intent(MainActivity.this, TypesActivity.class);
            startActivity(toTypes);
        }else if(item.getItemId() == R.id.about_menu){
            Intent toAbout = new Intent(MainActivity.this, AboutActivity.class);
            startActivity(toAbout);
        }else if(item.getItemId() == R.id.logout_menu){
            GlobalStorage.user = null;
            Intent toLogout = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(toLogout);
        }else if(item.getItemId() == R.id.saved_menu){
            Intent toSaved = new Intent(MainActivity.this, SavedActivity.class);
            startActivity(toSaved);
        }
        return true;
    }
}