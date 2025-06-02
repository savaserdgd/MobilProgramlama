package com.example.siirapp;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.siirapp.databinding.ActivityDashboardAdminBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class DashboardAdminActivity extends AppCompatActivity {
    private ActivityDashboardAdminBinding binding;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDashboardAdminBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Firebase Auth instance'ını al
        firebaseAuth = FirebaseAuth.getInstance();

        // Kullanıcı kontrolü yap
        checkUser();

        // Çıkış butonu onClick listener
        binding.logoutBtn.setOnClickListener(v -> {
            firebaseAuth.signOut();
            checkUser();
        });
    }

    private void checkUser() {
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if (currentUser == null) {
            // Kullanıcı giriş yapmamışsa MainActivity'ye yönlendir
            startActivity(new Intent(this, MainActivity.class));
            finish();
        } else {
            // Kullanıcı giriş yapmışsa email'i göster
            binding.subTitleTv.setText(currentUser.getEmail());
        }
    }
}