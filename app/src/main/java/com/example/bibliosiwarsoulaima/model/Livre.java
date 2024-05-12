package com.example.bibliosiwarsoulaima.model;

public class Livre {
    private int id;

    private String titre,auteur,image,categorie;
    private String description;

    public void setId(int id) {
        this.id = id;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRayon(String rayon) {
        this.rayon = rayon;
    }

    public void setArmoire(String armoire) {
        this.armoire = armoire;
    }

    public void setEtagere(String etagere) {
        this.etagere = etagere;
    }

    private  String rayon;
    private  String armoire;
    private  String etagere;
    public Livre(){
        super();
    }
    public Livre(String image, String titre, String categorie, String description,
                 String rayon, String armoire, String etagere, String auteur) {
        this.image = image;
        this.titre = titre;
        this.categorie = categorie;
        this.description = description;
        this.rayon = rayon;
        this.armoire = armoire;
        this.etagere = etagere;
        this.auteur = auteur;
    }


    public int getId() {
        return id;
    }

    public String getImage() {
        return image;
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

    public String getCategorie() {
        return categorie;
    }

    public String getDescription() {
        return description;
    }
    public String getTitre() {
        return titre;
    }

    public String getAuteur() {
        return auteur;
    }

}
