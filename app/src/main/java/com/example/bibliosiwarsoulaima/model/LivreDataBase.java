package com.example.bibliosiwarsoulaima.model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {LivreEntity.class}, version = 1, exportSchema = false)
public abstract class LivreDataBase extends RoomDatabase {
    private static LivreDataBase instance;

    public abstract LivreDao livreDao();

    public static synchronized LivreDataBase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            LivreDataBase.class, "livre_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
