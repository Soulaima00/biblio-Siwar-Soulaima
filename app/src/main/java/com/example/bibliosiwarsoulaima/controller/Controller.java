package com.example.bibliosiwarsoulaima.controller;
import android.content.Context;

import com.example.bibliosiwarsoulaima.model.JsonDataLoader;
import com.example.bibliosiwarsoulaima.model.Livre;
import com.example.bibliosiwarsoulaima.model.MaBaseDeDonnees;

import java.util.List;

public class Controller {
    private static Controller instance = null;
    private MaBaseDeDonnees base;

    private Controller(Context context) {
        base = new MaBaseDeDonnees(context);
        ajouterLivresDepuisJson(context);
    }

    public static Controller getInstance(Context context) {
        if (instance == null) {
            instance = new Controller(context);
        }
        return instance;
    }

    public void ajouterLivresDepuisJson(Context context) {
        JsonDataLoader js = new JsonDataLoader(context);
        List<Livre> listeLivre = js.chargerLivresDepuisJson();
        base.ajouterLivres(listeLivre);
    }

    public List<Livre> recupererLivres() {
        return base.lireLivres();
    }
    public void modifierEmplacement(Livre livre, String rayon, String armoire, String etagere) {
        livre.setRayon(rayon);
        livre.setArmoire(armoire);
        livre.setEtagere(etagere);
        base.modifierEmplacementLivre(livre.getId(), rayon,armoire,etagere);
    }
    public Livre recupererLivre(int id) {
        return base.lireLivreParId(id);
    }

}


