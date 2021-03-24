package com.example.inclass05;
/*
Assignment: HW03
File Name: AppGenreAdapter.java
Students (Group 14): Tyler Johnson, Chayathorn Akramongkolrojn
 */
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AppGenreAdapter extends RecyclerView.Adapter<AppGenreAdapter.AppGenreViewHolder>{
    ArrayList<String> genres;

    public AppGenreAdapter(ArrayList<String> genres) {
        this.genres = genres;
    }


    @NonNull
    @Override
    public AppGenreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_genre_list, parent, false);
        AppGenreViewHolder appGenreViewHolder = new AppGenreViewHolder(view);
        return appGenreViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AppGenreViewHolder holder, int position) {
        String genre = genres.get(position);
        holder.genre.setText(genre);
        holder.position = position;
    }

    @Override
    public int getItemCount() {
        return this.genres.size();
    }

    class AppGenreViewHolder extends RecyclerView.ViewHolder{
        TextView genre;
        View rootView;
        int position;
        public AppGenreViewHolder(@NonNull View itemView) {
            super(itemView);
            rootView = itemView;
            genre = itemView.findViewById(R.id.textViewGenreName);
        }
    }
}
