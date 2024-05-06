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

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText edit_text_search;
    private Controller controller;
    private RecyclerView recyclerView;
    private LivreAdapter livreAdapter;
    private List<Livre> livresList;
    private List<Livre> filteredList;
    FirebaseAuth mAuth;
    FirebaseUser user;

    Button log_out_button;

    @SuppressLint("MissingInflatedId")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        edit_text_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Ne rien faire ici
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String query = s.toString().toLowerCase().trim();
                if (query.isEmpty()) {
                    livreAdapter.restoreList(); // Restaurer la liste complète si le filtre est vide
                } else {
                    filterLivres(query);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Ne rien faire ici
            }
        });
        if (controller != null) {
            livresList=controller.ajouterLivresALaBase(this);

            if (livresList != null) {
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                livreAdapter = new LivreAdapter(livresList, this);
                recyclerView.setAdapter(livreAdapter);
            }
        }
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        if(user == null){
            Intent intent = new Intent(getApplicationContext() , LoginActivity.class);
            startActivity(intent);
            finish();
        }

        log_out_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext() , LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
    public void openSettingsActivity(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
    void init(){
        controller=Controller.getInstance();
        recyclerView = findViewById(R.id.recycler_view);
        edit_text_search=findViewById(R.id.edit_text_search);
        filteredList = new ArrayList<>();
        log_out_button = findViewById(R.id.log_out_button);


    }
    private void filterLivres(String query) {
        filteredList.clear();
        for (Livre livre : livresList) {
            if (livre.getTitre().toLowerCase().contains(query)) {
                filteredList.add(livre);
            }
        }
        livreAdapter.filterList(filteredList);
    }





}
