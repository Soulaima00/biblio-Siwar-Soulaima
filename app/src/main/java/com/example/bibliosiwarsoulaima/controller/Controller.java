package com.example.bibliosiwarsoulaima.controller;

import android.content.Context;

import com.example.bibliosiwarsoulaima.model.JsonDataLoader;
import com.example.bibliosiwarsoulaima.model.Livre;
import com.example.bibliosiwarsoulaima.model.LivreDao;
import com.example.bibliosiwarsoulaima.model.LivreDataBase;
import com.example.bibliosiwarsoulaima.model.LivreEntity;

import java.util.ArrayList;
import java.util.List;

public class Controller {

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
    public List<Livre> listerLivres(Context context){
         JsonDataLoader jsdata = new JsonDataLoader(context);
         return jsdata.chargerLivresDepuisJson();
    }
    public void ajouterLivresALaBase(Context context){
        List<Livre> livresList=listerLivres(context);
        LivreDataBase livreDatabase = LivreDataBase.getInstance(context);
        LivreDao livreDao = livreDatabase.livreDao();

        // Transformez les objets Livre en LivreEntity pour la base de données
        List<LivreEntity> livreEntities = new ArrayList<>();
        for (Livre livre : livresList) {
            LivreEntity livreEntity = new LivreEntity(livre.getId(),livre.getImage(),livre.getTitre(),
                    livre.getCategorie(),livre.getDescription(),livre.getEmplacement().getRayon(),
                    livre.getEmplacement().getArmoire(),livre.getEmplacement().getEtagere(),livre.getAuteur(),
                    livre.getNombreDePages());
            // Remplissez les champs de l'entité à partir de l'objet Livre
            livreEntities.add(livreEntity);
        }

        // Insérez les livres dans la base de données
        livreDao.insererLivres(livreEntities);

        // Maintenant, vous pouvez utiliser les données stockées dans la base de données Room
    }
}

