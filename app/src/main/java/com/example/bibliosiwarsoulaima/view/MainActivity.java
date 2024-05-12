package com.example.bibliosiwarsoulaima.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
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
import android.widget.Toast;


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
    private Button btnSettings;

    private static final int REQUEST_CODE_DETAIL_LIVRE = 100;

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
            livresList=controller.recupererLivres();

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

    }
    public void openSettingsActivity(View view) {
        mAuth.signOut();
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
    void init(){
        controller=Controller.getInstance(this);
        recyclerView = findViewById(R.id.recycler_view);
        edit_text_search=findViewById(R.id.edit_text_search);
        filteredList = new ArrayList<>();

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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_DETAIL_LIVRE && resultCode == RESULT_OK) {
            String nouveauRayon = data.getStringExtra("RAYON_RESULT");
            String nouvelleArmoire = data.getStringExtra("ARMOIRE_RESULT");
            String nouvelleEtagere = data.getStringExtra("ETAGERE_RESULT");
            String livreId = data.getStringExtra("LIVRE_ID");
            Livre livre =controller.recupererLivre(Integer.parseInt(livreId));
            controller.modifierEmplacement(livre,nouveauRayon,nouvelleArmoire,nouvelleEtagere);
            livreAdapter.setLivresList(controller.recupererLivres());
        }
    }

}
