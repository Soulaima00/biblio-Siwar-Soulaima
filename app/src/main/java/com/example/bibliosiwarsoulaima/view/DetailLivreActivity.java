package com.example.bibliosiwarsoulaima.view;

import static java.lang.Short.valueOf;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bibliosiwarsoulaima.R;

public class DetailLivreActivity extends AppCompatActivity {
    private TextView rayonTextView,armoireTextView,etagereTextView;
    private TextView titreTextView,auteurTextView, categorieTextView, emplacementTextView;
    private TextView descriptionTextView,descrTextView;
    private ImageView imageView;
    private Button btnempalcement, btnRetour;
    private String livreId ;
    private String titre,auteur,categorie,image,description;
    private static final int REQUEST_CODE_EMPLACEMENT = 200;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_livre);
        init();
        Intent intent = getIntent();
        if (intent != null) {
            livreId = intent.getStringExtra("LIVRE_ID");
            Toast.makeText(DetailLivreActivity.this, "livre "+livreId, Toast.LENGTH_SHORT).show();
            SharedPreferences sharedPreferences = getSharedPreferences("livrepref" + livreId, MODE_PRIVATE);
            titre = sharedPreferences.getString("LIVRE_TITRE", intent.getStringExtra("LIVRE_TITRE"));
            auteur = sharedPreferences.getString("LIVRE_AUTEUR", intent.getStringExtra("LIVRE_AUTEUR"));
            categorie = sharedPreferences.getString("LIVRE_CATEGORIE", intent.getStringExtra("LIVRE_CATEGORIE"));
            image = sharedPreferences.getString("LIVRE_IMAGE", intent.getStringExtra("LIVRE_IMAGE"));
            String rayon = sharedPreferences.getString("LIVRE_EMPLACEMENTRAYON", intent.getStringExtra("LIVRE_EMPLACEMENTRAYON"));
            String armoire = sharedPreferences.getString("LIVRE_EMPLACEMENTARMOIRE", intent.getStringExtra("LIVRE_EMPLACEMENTARMOIRE"));
            String etagere = sharedPreferences.getString("LIVRE_EMPLACEMENTETAGERE", intent.getStringExtra("LIVRE_EMPLACEMENTETAGERE"));
            description = sharedPreferences.getString("LIVRE_DESCRIPTION", intent.getStringExtra("LIVRE_DESCRIPTION"));

            imageView.setImageResource(this.getResources().getIdentifier(
                    image, "drawable", this.getPackageName()));
            titreTextView.setText(titre);
            auteurTextView.setText(auteur);
            categorieTextView.setText(categorie);
            emplacementTextView.setText("Emplacement :");
            rayonTextView.setText(rayon);
            armoireTextView.setText(armoire);
            etagereTextView.setText(etagere);
            descrTextView.setText("Description : ");
            descriptionTextView.setText(description);
            btnempalcement.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(DetailLivreActivity.this, Emplacement_Activity.class);
                    intent.putExtra("LIVRE_ID", livreId); // Pass the book ID
                    startActivityForResult(intent,REQUEST_CODE_EMPLACEMENT);
                }
            });
            btnRetour.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent resultIntent = new Intent(DetailLivreActivity.this, MainActivity.class);
                    resultIntent.putExtra("RAYON_RESULT", rayon);
                    resultIntent.putExtra("ARMOIRE_RESULT", armoire);
                    resultIntent.putExtra("ETAGERE_RESULT", etagere);
                    if (livreId != null) {
                        resultIntent.putExtra("LIVRE_ID", livreId);
                    }
                    setResult(RESULT_OK, resultIntent);
                    finish();
                }
            });

        }
    }
    public void init(){
        titreTextView = findViewById(R.id.titre_detail);
        auteurTextView = findViewById(R.id.auteur_detail);
        categorieTextView = findViewById(R.id.categorie_detail);
        imageView = findViewById(R.id.image_detail);
        emplacementTextView = findViewById(R.id.emplacement_detail);
        rayonTextView = findViewById(R.id.rayon);
        armoireTextView = findViewById(R.id.armoire);
        etagereTextView = findViewById(R.id.etagere);
        descriptionTextView = findViewById(R.id.description_detail);
        descrTextView = findViewById(R.id.desc);
        btnempalcement = findViewById(R.id.btnemplacement);
        btnRetour = findViewById(R.id.btnRetour);
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        SharedPreferences sharedPreferences = getSharedPreferences("livrepref" + livreId, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        if (requestCode == REQUEST_CODE_EMPLACEMENT && resultCode == RESULT_OK) {
            String nouveauRayon = data.getStringExtra("RAYON_RESULT");
            String nouvelleArmoire = data.getStringExtra("ARMOIRE_RESULT");
            String nouvelleEtagere = data.getStringExtra("ETAGERE_RESULT");
            if(!nouvelleEtagere.isEmpty() && !nouveauRayon.isEmpty() && !nouvelleEtagere.isEmpty()) {
                // Mettre à jour les SharedPreferences avec les nouvelles données d'emplacement
                editor.putString("LIVRE_EMPLACEMENTRAYON", nouveauRayon);
                editor.putString("LIVRE_EMPLACEMENTARMOIRE", nouvelleArmoire);
                editor.putString("LIVRE_EMPLACEMENTETAGERE", nouvelleEtagere);
                editor.apply();
            }

            Toast.makeText(DetailLivreActivity.this, "emplacement livre "+livreId, Toast.LENGTH_SHORT).show();

            rayonTextView.setText(nouveauRayon);
            armoireTextView.setText(nouvelleArmoire);
            etagereTextView.setText(nouvelleEtagere);

        }
    }
}

