package com.example.bibliosiwarsoulaima.model;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


public class MaBaseDeDonnees extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "biblioteque_db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_LIVRE = "livres";
    private static final String COL_ID = "id";
    private static final String COL_IMAGE = "image";
    private static final String COL_TITRE = "titre";
    private static final String COL_AUTEUR = "auteur";
    private static final String COL_CATEGORIE = "categorie";
    private static final String COL_DESCRIPTION = "description";
    private static final String COL_EMPLACEMENT_RAYON = "emplacement_rayon";
    private static final String COL_EMPLACEMENT_ARMOIRE = "emplacement_armoire";
    private static final String COL_EMPLACEMENT_ETAGERE = "emplacement_etagere";

    public MaBaseDeDonnees(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableLivre = "CREATE TABLE " + TABLE_LIVRE + "("
                + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COL_IMAGE + " TEXT,"
                + COL_TITRE + " TEXT,"
                + COL_AUTEUR + " TEXT,"
                + COL_CATEGORIE + " TEXT,"
                + COL_DESCRIPTION + " TEXT,"
                + COL_EMPLACEMENT_RAYON + " TEXT,"
                + COL_EMPLACEMENT_ARMOIRE + " TEXT,"
                + COL_EMPLACEMENT_ETAGERE + " TEXT"
                + ")";
        db.execSQL(createTableLivre);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LIVRE);
        onCreate(db);
    }

    public void ajouterLivre(Livre livre) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_IMAGE, livre.getImage());
        values.put(COL_TITRE, livre.getTitre());
        values.put(COL_AUTEUR, livre.getAuteur());
        values.put(COL_CATEGORIE, livre.getCategorie());
        values.put(COL_DESCRIPTION, livre.getDescription());
        values.put(COL_EMPLACEMENT_RAYON, livre.getRayon());
        values.put(COL_EMPLACEMENT_ARMOIRE, livre.getArmoire());
        values.put(COL_EMPLACEMENT_ETAGERE, livre.getEtagere());
        db.insert(TABLE_LIVRE, null, values);
        db.close();
    }
    public void ajouterLivres(List<Livre> livres) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values;
        for (Livre livre : livres) {
            values = new ContentValues();
            values.put(COL_IMAGE, livre.getImage());
            values.put(COL_TITRE, livre.getTitre());
            values.put(COL_AUTEUR, livre.getAuteur());
            values.put(COL_CATEGORIE, livre.getCategorie());
            values.put(COL_DESCRIPTION, livre.getDescription());
            values.put(COL_EMPLACEMENT_RAYON, livre.getRayon());
            values.put(COL_EMPLACEMENT_ARMOIRE, livre.getArmoire());
            values.put(COL_EMPLACEMENT_ETAGERE, livre.getEtagere());
            db.insert(TABLE_LIVRE, null, values);
        }
        db.close();
    }
    @SuppressLint("Range")
    public List<Livre> lireLivres() {
        List<Livre> livres = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_LIVRE, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                Livre livre = new Livre();
                livre.setId(cursor.getInt(cursor.getColumnIndex(COL_ID)));
                livre.setImage(cursor.getString(cursor.getColumnIndex(COL_IMAGE)));
                livre.setTitre(cursor.getString(cursor.getColumnIndex(COL_TITRE)));
                livre.setAuteur(cursor.getString(cursor.getColumnIndex(COL_AUTEUR)));
                livre.setCategorie(cursor.getString(cursor.getColumnIndex(COL_CATEGORIE)));
                livre.setDescription(cursor.getString(cursor.getColumnIndex(COL_DESCRIPTION)));
                livre.setRayon(cursor.getString(cursor.getColumnIndex(COL_EMPLACEMENT_RAYON)));
                livre.setArmoire(cursor.getString(cursor.getColumnIndex(COL_EMPLACEMENT_ARMOIRE)));
                livre.setEtagere(cursor.getString(cursor.getColumnIndex(COL_EMPLACEMENT_ETAGERE)));
                livres.add(livre);
            } while (cursor.moveToNext());
            cursor.close();
        }
        db.close();
        return livres;
    }
    public void modifierEmplacementLivre(int livreId, String nouvelEmplacementRayon, String nouvelleEmplacementArmoire, String nouvelEmplacementEtagere) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_EMPLACEMENT_RAYON, nouvelEmplacementRayon);
        values.put(COL_EMPLACEMENT_ARMOIRE, nouvelleEmplacementArmoire);
        values.put(COL_EMPLACEMENT_ETAGERE, nouvelEmplacementEtagere);
        db.update(TABLE_LIVRE, values, COL_ID + " = ?", new String[]{String.valueOf(livreId)});
        db.close();
    }
    @SuppressLint("Range")
    public Livre lireLivreParId(int livreId) {
        Livre livre = null;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_LIVRE + " WHERE " + COL_ID + " = ?", new String[]{String.valueOf(livreId)});
        if (cursor != null && cursor.moveToFirst()) {
            livre = new Livre();
            livre.setId(cursor.getInt(cursor.getColumnIndex(COL_ID)));
            livre.setImage(cursor.getString(cursor.getColumnIndex(COL_IMAGE)));
            livre.setTitre(cursor.getString(cursor.getColumnIndex(COL_TITRE)));
            livre.setAuteur(cursor.getString(cursor.getColumnIndex(COL_AUTEUR)));
            livre.setCategorie(cursor.getString(cursor.getColumnIndex(COL_CATEGORIE)));
            livre.setDescription(cursor.getString(cursor.getColumnIndex(COL_DESCRIPTION)));
            livre.setRayon(cursor.getString(cursor.getColumnIndex(COL_EMPLACEMENT_RAYON)));
            livre.setArmoire(cursor.getString(cursor.getColumnIndex(COL_EMPLACEMENT_ARMOIRE)));
            livre.setEtagere(cursor.getString(cursor.getColumnIndex(COL_EMPLACEMENT_ETAGERE)));
            cursor.close();
        }
        db.close();
        return livre;
    }


}
