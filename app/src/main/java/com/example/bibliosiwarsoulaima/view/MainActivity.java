package com.example.bibliosiwarsoulaima.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.example.bibliosiwarsoulaima.R;
import com.example.bibliosiwarsoulaima.controller.Controller;
import com.example.bibliosiwarsoulaima.model.Livre;
import com.example.bibliosiwarsoulaima.model.LivreAdapter;

import android.content.Intent;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import android.view.View;
import android.widget.Button;


import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Controller controller;
    private RecyclerView recyclerView;
    private LivreAdapter livreAdapter;
    private List<Livre> livresList;
    FirebaseAuth mAuth;
    FirebaseUser user;
    private Button btnSettings;


    @SuppressLint("MissingInflatedId")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        livresList = controller.listerLivres(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        livreAdapter = new LivreAdapter(livresList, this);
        recyclerView.setAdapter(livreAdapter);
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        if(user == null){
            Intent intent = new Intent(getApplicationContext() , LoginActivity.class);
            startActivity(intent);
            finish();
        }

    }
    public void openSettingsActivity(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
    void init(){
        controller=Controller.getInstance();
        recyclerView = findViewById(R.id.recycler_view);

    }
}
