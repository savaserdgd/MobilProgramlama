package com.example.siirapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.siirapp.databinding.ActivityAddPoemBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.HashMap;
import java.util.Map;

public class addPoemActivity extends AppCompatActivity {

    private ActivityAddPoemBinding binding;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference userPoemsRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddPoemBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();

        if (user == null) {
            finish();
            return;
        }

        userPoemsRef = FirebaseDatabase.getInstance().getReference("UserPoems").child(user.getUid());

        binding.backBtn.setOnClickListener(v -> finish());

        binding.SavePoemBtn.setOnClickListener(v -> savePoem());
    }

    private void savePoem() {
        String title = binding.titleEt.getText().toString().trim();
        String content = binding.ContentEt.getText().toString().trim();
        String author = binding.AuthorEt.getText().toString().trim();

        if (title.isEmpty()) {
            binding.titleEt.setError("Başlık giriniz");
            binding.titleEt.requestFocus();
            return;
        }

        if (content.isEmpty()) {
            binding.ContentEt.setError("Şiir içeriği giriniz");
            binding.ContentEt.requestFocus();
            return;
        }

        if (author.isEmpty()) {
            author = "Anonim";
        }

        Poem poem = new Poem(title, content, author);

        String poemId = userPoemsRef.push().getKey();
        if (poemId != null) {
            // Şiir başarıyla kaydedildiğinde:
            userPoemsRef.child(poemId)
                    .setValue(poem)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(this, "Şiir kaydedildi", Toast.LENGTH_SHORT).show();
                            setResult(RESULT_OK); // <- Bu satırı ekleyin
                            finish();
                        } else {
                            Toast.makeText(this, "Hata: " + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }
}