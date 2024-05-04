package com.example.bibliosiwarsoulaima.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "livres")
public class LivreEntity {
    @PrimaryKey(autoGenerate = true)
    private final int id;
    private final String image;
    private final String titre;

    public String getImage() {
        return image;
    }

    public String getTitre() {
        return titre;
    }

    public String getCategorie() {
        return categorie;
    }

    public String getDescription() {
        return description;
    }

    public String getRayon() {
        return rayon;
    }

    public String getArmoire() {
        return armoire;
    }

    public String getEtagere() {
        return etagere;
    }

    public String getAuteur() {
        return auteur;
    }


    public int getId() {
        return id;
    }

    private final String categorie;
    private final String description;
    private final String rayon;
    private final String armoire;
    private final String etagere;
    private final String auteur;

    public LivreEntity(int id, String image, String titre, String categorie, String description,
                       String rayon, String armoire, String etagere, String auteur) {
        this.id = id;
        this.image = image;
        this.titre = titre;
        this.categorie = categorie;
        this.description = description;
        this.rayon = rayon;
        this.armoire = armoire;
        this.etagere = etagere;
        this.auteur = auteur;
    }
}
