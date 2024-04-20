package com.example.bibliosiwarsoulaima.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface LivreDao {
    @Insert
    void insererLivre(LivreEntity livre);

    @Insert
    void insererLivres(List<LivreEntity> livres);

    @Query("SELECT * FROM livres")
    LiveData<List<LivreEntity>> obtenirTousLesLivres();
}

