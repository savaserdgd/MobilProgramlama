package com.example.siirapp;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.siirapp.databinding.ActivityDashboardUserBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class DashboardUserActivity extends AppCompatActivity {
    private ActivityDashboardUserBinding binding;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDashboardUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        firebaseAuth = FirebaseAuth.getInstance();
        checkUser();
        setupButtons();
    }

    private void checkUser() {
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user != null) {
            binding.userEmailTv.setText(user.getEmail());
        } else {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }
    }

    private void setupButtons() {
        binding.addPoemBtn.setOnClickListener(v -> {
            startActivity(new Intent(this, UserPoemsListActivity.class));
        });

        binding.homeBtn.setOnClickListener(v -> {
            startActivity(new Intent(this, PoemListActivity.class));
        });

        binding.logoutBtn.setOnClickListener(v -> {
            firebaseAuth.signOut();
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        });
    }
}