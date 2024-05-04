package com.example.bibliosiwarsoulaima.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.bibliosiwarsoulaima.R;
import com.example.bibliosiwarsoulaima.controller.Controller;
import com.example.bibliosiwarsoulaima.model.Livre;
import com.example.bibliosiwarsoulaima.model.LivreAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Controller controller;
    private RecyclerView recyclerView;
    private LivreAdapter livreAdapter;
    private List<Livre> livresList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        if (controller != null) {
            livresList=controller.ajouterLivresALaBase(this);

            if (livresList != null) {
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                livreAdapter = new LivreAdapter(livresList, this);
                recyclerView.setAdapter(livreAdapter);
            }
        }
    }
    void init(){
        controller=Controller.getInstance();
        recyclerView = findViewById(R.id.recycler_view);

    }
}