package com.example.bibliosiwarsoulaima.view;

import static java.lang.Short.valueOf;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bibliosiwarsoulaima.R;
import com.example.bibliosiwarsoulaima.controller.Controller;
import com.example.bibliosiwarsoulaima.model.LivreEntity;

public class DetailLivreActivity extends AppCompatActivity {
    private TextView rayonTextView,armoireTextView,etagereTextView;
    private TextView titreTextView,auteurTextView, categorieTextView, emplacementTextView;
    private TextView descriptionTextView,descrTextView;
    private ImageView imageView;
    private Button btnempalcement;
    private String livreId ;
    private String titre,auteur,categorie,image,description;
    private static final int REQUEST_CODE_EMPLACEMENT = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_livre);
        init();
        Intent intent = getIntent();
        if (intent != null) {
            livreId = intent.getStringExtra("LIVRE_ID");
            titre = intent.getStringExtra("LIVRE_TITRE");
            auteur = intent.getStringExtra("LIVRE_AUTEUR");
            categorie = intent.getStringExtra("LIVRE_CATEGORIE");
            image = intent.getStringExtra("LIVRE_IMAGE");
            String rayon = intent.getStringExtra("LIVRE_EMPLACEMENTRAYON");
            String armoire = intent.getStringExtra("LIVRE_EMPLACEMENTARMOIRE");
            String etagere = intent.getStringExtra("LIVRE_EMPLACEMENTETAGERE");
            description = intent.getStringExtra("LIVRE_DESCRIPTION");

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
                    startActivityForResult(intent,REQUEST_CODE_EMPLACEMENT);
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
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_EMPLACEMENT && resultCode == RESULT_OK) {
            String nouveauRayon = data.getStringExtra("RAYON_RESULT");
            String nouvelleArmoire = data.getStringExtra("ARMOIRE_RESULT");
            String nouvelleEtagere = data.getStringExtra("ETAGERE_RESULT");

            rayonTextView.setText(nouveauRayon);
            armoireTextView.setText(nouvelleArmoire);
            etagereTextView.setText(nouvelleEtagere);

            Controller controller = Controller.getInstance();
            if (controller != null) {
                controller.modifierEmplacementLivre(valueOf(livreId), nouveauRayon, nouvelleArmoire, nouvelleEtagere);
            }
        }
    }

}