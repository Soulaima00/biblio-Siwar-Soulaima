package com.example.bibliosiwarsoulaima.model;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class LivreDao {
    private LivreDBHelper dbHelper;

    public LivreDao(Context context) {
        dbHelper = new LivreDBHelper(context);
    }

    public void insererLivre(LivreEntity livre) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("image", livre.getImage());
        values.put("titre", livre.getTitre());
        values.put("categorie", livre.getCategorie());
        values.put("description", livre.getDescription());
        values.put("rayon", livre.getRayon());
        values.put("armoire", livre.getArmoire());
        values.put("etagere", livre.getEtagere());
        values.put("auteur", livre.getAuteur());

        db.insert("livres", null, values);
        db.close();
    }

    public void insererLivres(List<LivreEntity> livres) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        for (LivreEntity livre : livres) {
            ContentValues values = new ContentValues();
            values.put("image", livre.getImage());
            values.put("titre", livre.getTitre());
            values.put("categorie", livre.getCategorie());
            values.put("description", livre.getDescription());
            values.put("rayon", livre.getRayon());
            values.put("armoire", livre.getArmoire());
            values.put("etagere", livre.getEtagere());
            values.put("auteur", livre.getAuteur());
            db.insert("livres", null, values);
        }
        db.close();
    }

    public List<LivreEntity> obtenirTousLesLivres() {
        List<LivreEntity> livres = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM livres", null);

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex("id"));
                @SuppressLint("Range") String image = cursor.getString(cursor.getColumnIndex("image"));
                @SuppressLint("Range") String titre = cursor.getString(cursor.getColumnIndex("titre"));
                @SuppressLint("Range") String categorie = cursor.getString(cursor.getColumnIndex("categorie"));
                @SuppressLint("Range") String description = cursor.getString(cursor.getColumnIndex("description"));
                @SuppressLint("Range") String rayon = cursor.getString(cursor.getColumnIndex("rayon"));
                @SuppressLint("Range") String armoire = cursor.getString(cursor.getColumnIndex("armoire"));
                @SuppressLint("Range") String etagere = cursor.getString(cursor.getColumnIndex("etagere"));
                @SuppressLint("Range") String auteur = cursor.getString(cursor.getColumnIndex("auteur"));
                LivreEntity livre = new LivreEntity(id, image, titre, categorie,description,rayon,armoire,etagere,auteur);
                livres.add(livre);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return livres;
    }
    public void supprimerlivre(int id){
        dbHelper.supprimerLivre(id);
    }
    public void modifierEmplacementLivre(int idLivre, String nouveauRayon, String nouvelleArmoire, String nouvelleEtagere) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("rayon", nouveauRayon);
        values.put("armoire", nouvelleArmoire);
        values.put("etagere", nouvelleEtagere);

        String selection = "id=?";
        String[] selectionArgs = {String.valueOf(idLivre)};

        db.update("livres", values, selection, selectionArgs);

        db.close();
    }

}
