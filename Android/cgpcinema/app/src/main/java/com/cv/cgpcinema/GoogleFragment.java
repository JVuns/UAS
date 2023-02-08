package com.cv.cgpcinema;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class GoogleFragment extends Fragment {
    private GoogleMap mMap;
    public GoogleFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_gmaps, container, false);
        FragmentActivity fg = new Location();
//        SupportMapFragment mapFragment = (SupportMapFragment) fg.getSupportFragmentManager().findFragmentById(R.id.map);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.googleMap);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull GoogleMap googleMap) {
                mMap = googleMap;

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
        });

        return view;
    }


}