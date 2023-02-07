package com.cv.dosomethingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Gmaps extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("[JODEBUG] Hello");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gmaps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.googleMap);
        System.out.println("JODEBUG" + mapFragment);
        mapFragment.getMapAsync(this);
        GlobalStorage.smf = mapFragment;
    }

    public SupportMapFragment initialize(){
        System.out.println("[JODEBUG] Hello");
        setContentView(R.layout.activity_gmaps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.googleMap);
        System.out.println("JODEBUG" + mapFragment);
        mapFragment.getMapAsync(this);
        return mapFragment;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng Binus = new LatLng(-6.201935, 106.781525);
        mMap.addMarker(new MarkerOptions()
                .position(Binus)
                .title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(Binus));
    }



}