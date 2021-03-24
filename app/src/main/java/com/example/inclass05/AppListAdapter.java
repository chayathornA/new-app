package com.example.inclass05;
/*
Assignment: HW03
File Name: AppListAdapter.java
Students (Group 14): Tyler Johnson, Chayathorn Akramongkolrojn
 */
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AppListAdapter extends RecyclerView.Adapter<AppListAdapter.AppListViewHolder>{

    ArrayList<DataServices.App> apps;
    String token;
    String category;

    public AppListAdapter(ArrayList<DataServices.App> data, String token, String category) {
        this.apps = data;
        this.token = token;
        this.category = category;
    }


    @NonNull
    @Override
    public AppListAdapter.AppListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_app_list, parent, false);
        AppListAdapter.AppListViewHolder appListViewHolder = new AppListAdapter.AppListViewHolder(view);
        return appListViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AppListAdapter.AppListViewHolder holder, int position) {
        DataServices.App app = apps.get(position);
        holder.position = position;
        holder.appName.setText(app.name);
        holder.artistName.setText(app.artistName);
        holder.releaseDate.setText(app.releaseDate);
    }

    @Override
    public int getItemCount() {
        return this.apps.size();
    }

    class AppListViewHolder extends RecyclerView.ViewHolder {
        TextView appName;
        TextView artistName;
        TextView releaseDate;
        int position;

        public AppListViewHolder(@NonNull View itemView) {
            super(itemView);
            appName = itemView.findViewById(R.id.textViewAppName);
            artistName = itemView.findViewById(R.id.textViewArtistName);
            releaseDate = itemView.findViewById(R.id.textViewReleaseDate);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (v.getContext() instanceof AppListFragment.AppListListener) {
                        AppListFragment.AppListListener context = (AppListFragment.AppListListener)v.getContext();
                        context.openAppDetails(token, apps.get(position), category);
                    }
                }
            });
        }
    }

}
