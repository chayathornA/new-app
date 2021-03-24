package com.example.inclass05;
/*
Assignment: HW03
File Name: CategoriesListAdapter.java
Students (Group 14): Tyler Johnson, Chayathorn Akramongkolrojn
 */
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CategoriesListAdapter extends RecyclerView.Adapter<CategoriesListAdapter.CategoriesViewHolder> {

    ArrayList<String> categories;
    String token;

    public CategoriesListAdapter(ArrayList<String> data, String token) {
        categories = data;
        this.token = token;
    }


    @NonNull
    @Override
    public CategoriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_categories_list, parent, false);
        CategoriesViewHolder categoriesViewHolder = new CategoriesViewHolder(view);
        return categoriesViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriesViewHolder holder, int position) {
        String category = categories.get(position);
        holder.name.setText(category);
        holder.position = position;
    }


    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class CategoriesViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        View rootView;
        int position;
        public CategoriesViewHolder(@NonNull View itemView) {
            super(itemView);
            rootView = itemView;
            name = itemView.findViewById(R.id.textViewGenreName);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (v.getContext() instanceof AppCategoriesFragment.AppCategoriesListener) {
                        AppCategoriesFragment.AppCategoriesListener context = (AppCategoriesFragment.AppCategoriesListener)v.getContext();
                        context.appListDisplay(categories.get(position), token);
                    }
                }
            });
        }
    }
}
