package com.example.inclass05;
/*
Assignment: HW03
File Name: AppAdapter.java
Students (Group 14): Tyler Johnson, Chayathorn Akramongkolrojn
 */
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class AppAdapter extends ArrayAdapter<DataServices.App> {


    public AppAdapter(@NonNull Context context, int resource, @NonNull ArrayList<DataServices.App> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.layout_app_list, parent, false);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.appName = convertView.findViewById(R.id.textViewAppName);
            viewHolder.artistName = convertView.findViewById(R.id.textViewArtistName);
            viewHolder.releaseDate = convertView.findViewById(R.id.textViewReleaseDate);
            convertView.setTag(viewHolder);
        }


        DataServices.App app = getItem(position);

        ViewHolder viewHolder = (ViewHolder)convertView.getTag();
        viewHolder.appName.setText(app.name);
        viewHolder.artistName.setText(app.artistName);
        viewHolder.releaseDate.setText(app.releaseDate);

        return convertView;
    }

    class ViewHolder {
        TextView appName;
        TextView artistName;
        TextView releaseDate;
    }
}
