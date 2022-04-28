package com.ericciria.m7projecte02;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class A03_Mapa extends FragmentActivity implements OnMapReadyCallback, LocationListener {

    private static final int PERMISSION_REQUEST_POSITION = 100;
    private GoogleMap mMap;
    private LatLng joviat = new LatLng(41.721488333333334, 1.818435);
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a03_mapa);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //control de permisos
        requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},PERMISSION_REQUEST_POSITION);

        //creem un gestor de posicio per actualitzar el mapa
        LocationManager gestorLoc = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission
                (this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            Toast.makeText(this, "S'ha de donar permís per utilitzar la localització", Toast.LENGTH_SHORT).show();
            return;
        }
        gestorLoc.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, this);


    }

    public void anarAMapa (View v) {
        Intent mapejar = new Intent(this, A03_Mapa.class);
        startActivity(mapejar);

    }

    public void anarAArtistes (View x) {
        Intent artistejar = new Intent(this, A05_Artistes.class);
        startActivity(artistejar);

    }

    public void anarAEscultures (View y) {

        Intent escultpturar = new Intent(this, A04_Escultures.class);
        startActivity(escultpturar);

    }

    public void anarAFundcacio (View z) {


        Intent fundar = new Intent(this, A02_Fundacio.class);
        startActivity(fundar);

    }

    @Override
    public void onLocationChanged(@NonNull Location location) {

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng origin = new LatLng(41.760223, 2.014856);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(origin));
        //per fer zoom en el dispositiu fisic
        UiSettings settings = mMap.getUiSettings();
        settings.setZoomControlsEnabled(true);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(origin, 15.0f));

        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        db = FirebaseFirestore.getInstance();
        db.collection("escultures")
                //.document("asd1")
                .whereGreaterThan("altura",0)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                LatLng pos = new LatLng(Double.valueOf(document.get("latitud").toString()),
                                        Double.valueOf(document.get("longitud").toString()));
                                mMap.addMarker(new MarkerOptions().position(pos).title("Escultura: " + document.get("idEscultura").toString()));
                            }

                        } else {
                            Toast.makeText(A03_Mapa.this, "No existeix", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}