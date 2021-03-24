package com.example.inclass05;
/*
Assignment: HW03
File Name: AppDetailsFragment.java
Students (Group 14): Tyler Johnson, Chayathorn Akramongkolrojn
 */
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class AppDetailsFragment extends Fragment {

    //parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "token";
    private static final String ARG_PARAM2 = "app";
    private static final String ARG_PARAM3 = "category";

    // types of parameters
    private String token;
    private DataServices.App app;
    private String category;

    //data
    ArrayList<String> genres;

    //UI elements
    TextView appName;
    TextView artistName;
    TextView releaseDate;
    RecyclerView genreList;
    LinearLayoutManager linearLayoutManager;
    AppGenreAdapter adapter;

    public AppDetailsFragment() {
        // Required empty public constructor
    }

    public static AppDetailsFragment newInstance(String param1, DataServices.App param2, String category) {
        AppDetailsFragment fragment = new AppDetailsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putSerializable(ARG_PARAM2, param2);
        args.putString(ARG_PARAM3, category);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            token = getArguments().getString(ARG_PARAM1);
            app = (DataServices.App)getArguments().getSerializable(ARG_PARAM2);
            category = getArguments().getString(ARG_PARAM3);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_app_details, container, false);

        //app details assign
        appName = view.findViewById(R.id.textViewAppDetailsName);
        appName.setText(app.name);
        artistName = view.findViewById(R.id.textViewAppDetailsArtist);
        artistName.setText(app.artistName);
        releaseDate = view.findViewById(R.id.textViewAppDetailsReleaseDate);
        releaseDate.setText(app.releaseDate);

        //genre list populate
        genreList = view.findViewById(R.id.recyclerViewGenreList);
        genres = app.genres;
        adapter = new AppGenreAdapter(genres);
        linearLayoutManager = new LinearLayoutManager(getContext());
        genreList.setLayoutManager(linearLayoutManager);
        genreList.setAdapter(adapter);

        return view;
    }

    @Override
    public void onDestroy() {
        appDetailsListener.closeAppDetails(category);
        super.onDestroy();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof AppDetailsListener) appDetailsListener = (AppDetailsListener)context;
        else throw new RuntimeException(context.toString() + "must be an instance of AppDetailslistener");
    }

    AppDetailsListener appDetailsListener;
    public interface AppDetailsListener {
        void closeAppDetails(String category);
    }
}