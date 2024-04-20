package com.example.bibliosiwarsoulaima.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "livres")
public class LivreEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String image;
    private String titre;

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

    public int getNombreDePages() {
        return nombreDePages;
    }

    public int getId() {
        return id;
    }

    private String categorie;
    private String description;
    private String rayon;
    private String armoire;
    private String etagere;
    private String auteur;
    private int nombreDePages;

    public LivreEntity(int id, String image, String titre, String categorie, String description,
                       String rayon, String armoire, String etagere, String auteur, int nombreDePages) {
        this.id = id;
        this.image = image;
        this.titre = titre;
        this.categorie = categorie;
        this.description = description;
        this.rayon = rayon;
        this.armoire = armoire;
        this.etagere = etagere;
        this.auteur = auteur;
        this.nombreDePages = nombreDePages;
    }
}
