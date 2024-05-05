package com.example.bibliosiwarsoulaima.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bibliosiwarsoulaima.R;

public class Emplacement_Activity extends AppCompatActivity {
    EditText etrayon,etarmoire,etetagere;
    Button btnSubmiter,btnRetour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emplacement);
        init();
        btnSubmiter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String rayon = etrayon.getText().toString();
                String armoire = etarmoire.getText().toString();
                String etagere = etetagere.getText().toString();
                if (rayon.isEmpty() || armoire.isEmpty() || etagere.isEmpty()) {
                    Toast.makeText(Emplacement_Activity.this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                } else {
                    Intent resultIntent = new Intent(Emplacement_Activity.this, DetailLivreActivity.class);
                    resultIntent.putExtra("RAYON_RESULT", rayon);
                    resultIntent.putExtra("ARMOIRE_RESULT", armoire);
                    resultIntent.putExtra("ETAGERE_RESULT", etagere);
                    setResult(RESULT_OK, resultIntent);
                    finish();
                }
            }
        });
        btnRetour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Emplacement_Activity.this, DetailLivreActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
    void init(){
        etrayon=findViewById(R.id.etrayon);
        etarmoire=findViewById(R.id.etarmoire);
        etetagere=findViewById(R.id.etetagere);
        btnSubmiter=findViewById(R.id.btnsubmit);
        btnRetour = findViewById(R.id.btnRetour);
    }
}