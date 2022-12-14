package br.ufc.quixada.scap;

import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class Pet extends AppCompatActivity  implements OnMapReadyCallback, LocationListener {


    BottomNavigationView bottomNavigationView;

    GoogleMap map;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet);


        bottomNavigationView = findViewById(R.id.bottom_menu);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        bottomNavigationView.setSelectedItemId(R.id.bottom_menu_pet );

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId())
                {
                    case R.id.bottom_menu_atividades:

                        startActivity(new Intent(getApplicationContext(), ListarAtividade.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.bottom_menu_inicio:

                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.bottom_menu_adicionar:
                        startActivity(new Intent(getApplicationContext(),FormAddAtividade.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.bottom_menu_pet:

                        return true;
                }
                return false;
            }
        });



    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        map = googleMap;

        com.google.android.gms.maps.model.LatLng UFC = new com.google.android.gms.maps.model.LatLng(-4.979813, -39.056500);
        map.addMarker(new MarkerOptions().position(UFC).title("UFC - Campus Quixadá"));
        map.moveCamera(CameraUpdateFactory.newLatLng(UFC));

    }

    @Override
    public void onLocationChanged(@NonNull Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        LocationListener.super.onStatusChanged(provider, status, extras);
    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {
        LocationListener.super.onProviderEnabled(provider);
    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {
        LocationListener.super.onProviderDisabled(provider);
    }
}

