package com.example.bibliosiwarsoulaima.model;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bibliosiwarsoulaima.R;
import com.example.bibliosiwarsoulaima.view.DetailLivreActivity;

import java.util.ArrayList;
import java.util.List;

public class LivreAdapter extends RecyclerView.Adapter<LivreAdapter.LivreViewHolder> {

    private List<Livre> livresList;
    private List<Livre> filteredList;
    private Context context;
    private static final int REQUEST_CODE_DETAIL_LIVRE = 100;

    public LivreAdapter(List<Livre> livresList, Context context) {
        this.livresList = livresList;
        this.filteredList = new ArrayList<>(livresList);
        this.context = context;
    }
    public void filterList(List<Livre> filteredList) {
        this.filteredList = filteredList;
        notifyDataSetChanged();
    }

    public void setLivresList(List<Livre> livresList) {
        this.livresList = livresList;
    }

    public void setFilteredList(List<Livre> filteredList) {
        this.filteredList = filteredList;
    }

    public void restoreList() {
        filteredList.clear();
        filteredList.addAll(livresList);
        notifyDataSetChanged();
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
        Livre livre = filteredList.get(position);
        holder.imageLivre.setImageResource(context.getResources().getIdentifier(
                livre.getImage(), "drawable", context.getPackageName()));
        holder.titreLivre.setText(livre.getTitre());
        holder.auteurLivre.setText(livre.getAuteur());
        holder.categorieLivre.setText(livre.getCategorie());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int livreId=livre.getId();
                Intent intent = new Intent(context, DetailLivreActivity.class);
                intent.putExtra("LIVRE_ID", String.valueOf(livreId));
                intent.putExtra("LIVRE_TITRE", livre.getTitre());
                intent.putExtra("LIVRE_AUTEUR", livre.getAuteur());
                intent.putExtra("LIVRE_CATEGORIE", livre.getCategorie());
                intent.putExtra("LIVRE_IMAGE", livre.getImage());
                intent.putExtra("LIVRE_EMPLACEMENTRAYON", livre.getRayon());
                intent.putExtra("LIVRE_EMPLACEMENTARMOIRE", livre.getArmoire());
                intent.putExtra("LIVRE_EMPLACEMENTETAGERE", livre.getEtagere());
                intent.putExtra("LIVRE_DESCRIPTION", livre.getDescription());
                ((AppCompatActivity) context).startActivityForResult(intent, REQUEST_CODE_DETAIL_LIVRE);
            }
        });
    }

    @Override
    public int getItemCount() {
        return filteredList.size();
    }
}

