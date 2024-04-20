package com.example.bibliosiwarsoulaima.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bibliosiwarsoulaima.R;
import com.squareup.picasso.Picasso;

public class DetailLivreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_livre);

        // Récupérer les données du livre à partir de l'Intent
        Intent intent = getIntent();
        if (intent != null) {
            String livreId = ((Intent) intent).getStringExtra("LIVRE_ID");
            String titre = intent.getStringExtra("LIVRE_TITRE");
            String auteur = intent.getStringExtra("LIVRE_AUTEUR");
            String categorie = intent.getStringExtra("LIVRE_CATEGORIE");
            String image = intent.getStringExtra("LIVRE_IMAGE");
            int nbrePage = intent.getIntExtra("LIVRE_NBREPAGE",0);
            int nbreCopie = intent.getIntExtra("LIVRE_NBRECOPIE",1);
            String emplacement = intent.getStringExtra("LIVRE_EMPLACEMENT");
            String description = intent.getStringExtra("LIVRE_DESCRIPTION");


            // Utiliser ces données pour afficher les détails du livre dans l'activité
            TextView titreTextView = findViewById(R.id.titre_detail);
            TextView auteurTextView = findViewById(R.id.auteur_detail);
            TextView categorieTextView = findViewById(R.id.categorie_detail);
            ImageView imageView=findViewById(R.id.image_detail);
            TextView nbrepageTextView = findViewById(R.id.nbrePage_detail);
            TextView nbreCopieTextView = findViewById(R.id.nbreCopie_detail);
            TextView emplacementTextView = findViewById(R.id.emplacement_detail);
            TextView descriptionTextView = findViewById(R.id.description_detail);
            imageView.setImageResource(this.getResources().getIdentifier(
                    image, "drawable", this.getPackageName()));
            titreTextView.setText(titre);
            auteurTextView.setText(auteur);
            categorieTextView.setText(categorie);
            nbrepageTextView.setText("Nombre de Pages: "+nbrePage);
            nbreCopieTextView.setText("Nombre de Copie: "+nbreCopie);
            emplacementTextView.setText(emplacement);
            descriptionTextView.setText("Description : "+description);

        }
    }
}
