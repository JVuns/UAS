package com.cv.cgpcinema;

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
        LatLng cinemaAlpha = new LatLng(-6.193924061113853, 106.78813220277623);
        LatLng cinemaBeta = new LatLng(-6.20175020412279, 106.78223868546155);
        mMap.addMarker(new MarkerOptions()
                .position(cinemaAlpha)
                .title("Cinema Alpha"));

        mMap.addMarker(new MarkerOptions()
                .position(cinemaBeta)
                .title("Cinema Beta"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(cinemaAlpha));
    }



}