package com.example.siirapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SplashActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        firebaseAuth = FirebaseAuth.getInstance();

        new Handler().postDelayed(this::checkUser, 2000);
    }

    private void checkUser() {
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if (currentUser == null) {
            // Kullanıcı giriş yapmamışsa
            startActivity(new Intent(this, MainActivity.class));
            finish();
        } else {
            // Kullanıcı giriş yapmışsa, tipini kontrol et
            checkUserType(currentUser.getUid());
        }
    }

    private void checkUserType(String uid) {
        DatabaseReference userRef = FirebaseDatabase.getInstance()
                .getReference("Users")
                .child(uid);

        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String userType = snapshot.child("userType").getValue(String.class);
                    redirectToProperDashboard(userType);
                } else {
                    // Kullanıcı verisi yoksa
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    finish();
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Hata durumunda
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        });
    }

    private void redirectToProperDashboard(String userType) {
        Class<?> destinationActivity;

        if ("admin".equals(userType)) {
            destinationActivity = DashboardAdminActivity.class;
        } else {
            // Varsayılan olarak user kabul et
            destinationActivity = DashboardUserActivity.class;
        }

        startActivity(new Intent(this, destinationActivity));
        finish();
    }
}