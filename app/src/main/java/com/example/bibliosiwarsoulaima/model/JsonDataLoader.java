package com.example.bibliosiwarsoulaima.model;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import android.content.Context;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class JsonDataLoader {
    private final Context context;

    public JsonDataLoader(Context context) {
        this.context = context;
    }

    public List<Livre> chargerLivresDepuisJson() {
        List<Livre> livresList = new ArrayList<>();
        try {
            InputStream inputStream = context.getAssets().open("Bibliothèque.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            String json = new String(buffer, StandardCharsets.UTF_8);

            Gson gson = new Gson();
            Type listeType = new TypeToken<List<Livre>>(){}.getType();
            livresList = gson.fromJson(json, listeType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return livresList;
    }
}
