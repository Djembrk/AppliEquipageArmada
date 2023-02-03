package com.example.appliequipage;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;


    HomeFragment homeFragment = new HomeFragment();
    CompteurFragment compteurFragment = new CompteurFragment();
    QRCodeFragment qrCodeFragment = new QRCodeFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, homeFragment).commit();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {

            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,homeFragment).commit();
                        return true;
                    case R.id.scanner:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,qrCodeFragment).commit();
                        return true;
                    case R.id.ajouter:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,compteurFragment).commit();
                        return true;
                }

                return false;
            }
        });
    }
}