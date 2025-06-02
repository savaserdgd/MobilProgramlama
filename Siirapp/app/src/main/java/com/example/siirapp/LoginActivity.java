package com.example.siirapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.siirapp.databinding.ActivityLoginBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Lütfen Bekleyin");
        progressDialog.setCanceledOnTouchOutside(false);

        binding.noAccountTv.setOnClickListener(v ->
                startActivity(new Intent(this, RegisterActivity.class)));

        binding.loginBtn.setOnClickListener(v -> validateData());
    }

    private void validateData() {
        String email = binding.emailEt.getText().toString().trim();
        String password = binding.passwordEt.getText().toString().trim();

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.emailEt.setError("Geçersiz e-posta formatı!");
        } else if (TextUtils.isEmpty(password)) {
            binding.passwordEt.setError("Şifre boş olamaz!");
        } else {
            loginUser(email, password);
        }
    }

    private void loginUser(String email, String password) {
        progressDialog.setMessage("Giriş Yapılıyor...");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener(authResult -> {
                    FirebaseUser user = firebaseAuth.getCurrentUser();
                    if (user != null) {
                        checkUserType(user.getUid());
                    } else {
                        progressDialog.dismiss();
                        Toast.makeText(this, "Kullanıcı bulunamadı!", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(e -> {
                    progressDialog.dismiss();
                    Log.e("LoginActivity", "Giriş hatası: " + e.getMessage());
                    Toast.makeText(this, "Giriş başarısız: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }

    private void checkUserType(String uid) {
        progressDialog.setMessage("Kullanıcı Tipi Kontrol Ediliyor...");

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users");
        ref.child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                progressDialog.dismiss();

                if (!snapshot.exists()) {
                    Toast.makeText(LoginActivity.this, "Kullanıcı verileri eksik!", Toast.LENGTH_SHORT).show();
                    return;
                }

                String userType = snapshot.child("userType").getValue(String.class);
                if (userType == null) {
                    Toast.makeText(LoginActivity.this, "Kullanıcı tipi tanımlı değil!", Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent intent;
                if (userType.equalsIgnoreCase("admin")) {
                    intent = new Intent(LoginActivity.this, DashboardAdminActivity.class);
                } else {
                    intent = new Intent(LoginActivity.this, DashboardUserActivity.class);
                }
                startActivity(intent);
                finish();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                progressDialog.dismiss();
                Log.e("LoginActivity", "Veritabanı hatası: " + error.getMessage());
                Toast.makeText(LoginActivity.this, "Veritabanı hatası: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}