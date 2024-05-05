package com.example.bibliosiwarsoulaima.controller;

import android.content.Context;

import com.example.bibliosiwarsoulaima.model.JsonDataLoader;
import com.example.bibliosiwarsoulaima.model.Livre;
import com.example.bibliosiwarsoulaima.model.LivreDBHelper;
import com.example.bibliosiwarsoulaima.model.LivreDao;
import com.example.bibliosiwarsoulaima.model.LivreEntity;

import java.util.ArrayList;
import java.util.List;

public class Controller {
    public static LivreDao livreDao;
    private static Controller instance =null;
    private Controller(){
        super();
    }
    public static final Controller getInstance(){
        if(instance==null){
            instance=new Controller();
        }
        return instance;

    }

    public List<Livre> ajouterLivresALaBase(Context context) {
        JsonDataLoader jsdata = new JsonDataLoader(context);
        List<Livre> livresList=jsdata.chargerLivresDepuisJson();

        // Transformez les objets Livre en LivreEntity pour la base de données
        List<LivreEntity> livreEntities = new ArrayList<>();
        for (Livre livre : livresList) {
            LivreEntity livreEntity = new LivreEntity(livre.getId(), livre.getImage(), livre.getTitre(),
                    livre.getCategorie(), livre.getDescription(), livre.getEmplacement().getRayon(),
                    livre.getEmplacement().getArmoire(), livre.getEmplacement().getEtagere(), livre.getAuteur());
            livreEntities.add(livreEntity);
        }
        if (livreDao == null) {
            livreDao = new LivreDao(context);
        }
        if (livreDao != null) {
            livreDao.insererLivres(livreEntities);
        }
        return livresList;
    }
    public void supprimerLivreDeLaBase(int id){
        if (livreDao != null) {
            livreDao.supprimerlivre(id);
        }
    }

    public void ajouterLivreALaBase(LivreEntity livre) {
        if (livreDao != null) {
            livreDao.insererLivre(livre);
        }
    }
    public void modifierEmplacementLivre(int idLivre, String nouveauRayon, String nouvelleArmoire, String nouvelleEtagere) {
        // Mettre à jour l'emplacement du livre dans la base de données locale
        livreDao.modifierEmplacementLivre(idLivre, nouveauRayon, nouvelleArmoire, nouvelleEtagere);
    }


}

