package com.example.bibliosiwarsoulaima.model;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bibliosiwarsoulaima.R;
import com.example.bibliosiwarsoulaima.view.DetailLivreActivity;

import java.util.List;

public class LivreAdapter extends RecyclerView.Adapter<LivreAdapter.LivreViewHolder> {

    private final List<Livre> livresList;
    private final Context context;

    public LivreAdapter(List<Livre> livresList, Context context) {
        this.livresList = livresList;
        this.context = context;
    }

    public static class LivreViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageLivre;
        public TextView titreLivre;
        public TextView auteurLivre;
        public TextView categorieLivre;


        public LivreViewHolder(View view) {
            super(view);
            imageLivre = view.findViewById(R.id.image_livre);
            titreLivre = view.findViewById(R.id.titre_livre);
            auteurLivre = view.findViewById(R.id.auteur_livre);
            categorieLivre = view.findViewById(R.id.categorie_livre);
        }
    }

    @NonNull
    @Override
    public LivreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.livre, parent, false);
        return new LivreViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull LivreViewHolder holder, int position) {
        Livre livre = livresList.get(position);
        holder.imageLivre.setImageResource(context.getResources().getIdentifier(
                livre.getImage(), "drawable", context.getPackageName()));
        holder.titreLivre.setText(livre.getTitre());
        holder.auteurLivre.setText(livre.getAuteur());
        holder.categorieLivre.setText(livre.getCategorie());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailLivreActivity.class);
                // Passer les données du livre à l'activité de détail
                intent.putExtra("LIVRE_ID", livre.getId()); // Supposons que getId() retourne l'ID du livre
                intent.putExtra("LIVRE_TITRE", livre.getTitre());
                intent.putExtra("LIVRE_AUTEUR", livre.getAuteur());
                intent.putExtra("LIVRE_CATEGORIE", livre.getCategorie());
                intent.putExtra("LIVRE_IMAGE", livre.getImage());
                intent.putExtra("LIVRE_EMPLACEMENTRAYON", livre.getEmplacement().getRayon());
                intent.putExtra("LIVRE_EMPLACEMENTARMOIRE", livre.getEmplacement().getArmoire());
                intent.putExtra("LIVRE_EMPLACEMENTETAGERE", livre.getEmplacement().getEtagere());
                intent.putExtra("LIVRE_DESCRIPTION", livre.getDescription());

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return livresList.size();
    }
}

