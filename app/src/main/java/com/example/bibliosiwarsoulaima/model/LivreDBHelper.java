package com.example.bibliosiwarsoulaima.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class LivreDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "livre_database";
    private static final int DATABASE_VERSION = 1;

    public LivreDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE livres (id INTEGER PRIMARY KEY AUTOINCREMENT,image TEXT, titre TEXT, categorie TEXT, description TEXT, rayon TEXT, armoire TEXT, etagere TEXT, auteur TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS livres");
        onCreate(db);
    }

    public void supprimerLivre(int idLivre) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("livres", "id=?", new String[]{String.valueOf(idLivre)});
        db.close();
    }
}
