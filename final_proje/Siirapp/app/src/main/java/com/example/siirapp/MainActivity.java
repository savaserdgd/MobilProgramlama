package com.example.siirapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import com.example.siirapp.databinding.ActivityMainBinding; // Bu import satırını doğru şekilde eklediğinizden emin olun

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding; // Binding sınıfı

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater()); // Inflate işlemi
        setContentView(binding.getRoot()); // Root view'u set et

        binding.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));

            }
        });
        binding.skipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, DashboardUserActivity.class));

            }
        });

    }
}
