package com.example.siirapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.siirapp.databinding.ActivityUserPoemsListBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

public class UserPoemsListActivity extends AppCompatActivity {

    private ActivityUserPoemsListBinding binding;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference userPoemsRef;
    private PoemAdapter adapter;
    private List<Poem> poemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUserPoemsListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Initialize Firebase
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();

        if (user == null) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
            return;
        }

        // Setup Firebase reference
        userPoemsRef = FirebaseDatabase.getInstance().getReference("UserPoems").child(user.getUid());

        // Initialize list and adapter
        poemList = new ArrayList<>();
        adapter = new PoemAdapter(this, poemList);

        // Setup RecyclerView
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(adapter);

        // Button click listeners
        binding.backBtn.setOnClickListener(v -> finish());

        binding.addNewPoemBtn.setOnClickListener(v -> {
            // Start addPoemActivity and wait for result
            startActivityForResult(
                    new Intent(this, addPoemActivity.class),
                    1001 // Request code
            );
        });

        // Load poems
        loadUserPoems();
    }

    private void loadUserPoems() {
        userPoemsRef.orderByChild("timestamp").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                poemList.clear();

                for (DataSnapshot ds : snapshot.getChildren()) {
                    Poem poem = ds.getValue(Poem.class);
                    if (poem != null) {
                        poemList.add(0, poem); // Newest first
                    }
                }

                adapter.notifyDataSetChanged();
                updateEmptyState();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(UserPoemsListActivity.this,
                        "Error: " + error.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateEmptyState() {
        if (poemList.isEmpty()) {
            binding.emptyState.setVisibility(View.VISIBLE);
            binding.recyclerView.setVisibility(View.GONE);
        } else {
            binding.emptyState.setVisibility(View.GONE);
            binding.recyclerView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1001 && resultCode == RESULT_OK) {
            // Refresh the list when returning from addPoemActivity
            loadUserPoems();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Refresh data when activity resumes
        loadUserPoems();
    }
}