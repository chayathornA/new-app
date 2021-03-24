package com.example.inclass05;
/*
Assignment: HW03
File Name: AppCategoriesFragment.java
Students (Group 14): Tyler Johnson, Chayathorn Akramongkolrojn
 */
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class AppCategoriesFragment extends Fragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1_TOKEN = "param1";
    private String token;

    TextView username;
    DataServices.Account accountInfo;

    //list elements
    //TODO change to RecyclerView
    RecyclerView listViewCategories;
    ArrayList<String> categoryNames;
    CategoriesListAdapter adapter;
    LinearLayoutManager linearLayoutManager;

    public AppCategoriesFragment() {
        // Required empty public constructor
    }

    public static AppCategoriesFragment newInstance(String token) {
        AppCategoriesFragment fragment = new AppCategoriesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1_TOKEN, token);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            token = getArguments().getString(ARG_PARAM1_TOKEN);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_app_categories, container, false);

        //retrieves account
        DataServices.getAccount(token, new DataServices.AccountResponse() {
            @Override
            public void onSuccess(DataServices.Account account) {
                accountInfo = account;
            }

            @Override
            public void onFailure(DataServices.RequestException exception) {
                Toast.makeText(view.getContext(), exception.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        //sets username text with data from account
        Log.d("myApp", "setting username text");
        username = view.findViewById(R.id.textViewAppUsername);
        username.setText("Welcome " + accountInfo.getName());

        //gets categories
        //TODO change to RecyclerView
        Log.d("myApp", "attempting data retrieval");
        DataServices.getAppCategories(token, new DataServices.DataResponse<String>() {
            @Override
            public void onSuccess(ArrayList<String> data) {
                categoryNames = data;
                adapter = new CategoriesListAdapter(categoryNames, token);
                Log.d("myApp", "app category retrieval successful");
                Log.d("myApp", categoryNames.toString());
            }

            @Override
            public void onFailure(DataServices.RequestException exception) {
                Toast.makeText(view.getContext(), exception.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("myApp", "app category retrieval unsuccessful");
            }
        });

        //initialize adapter and list
        //TODO change to RecyclerView
        listViewCategories = view.findViewById(R.id.recyclerViewAppCategories);
        listViewCategories.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(getContext());
        listViewCategories.setLayoutManager(linearLayoutManager);
        listViewCategories.setAdapter(adapter);


        //logout functionality
        view.findViewById(R.id.buttonAppCategoriesLogout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appCategoriesListener.logout();
            }
        });

        return view;
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof AppCategoriesListener) appCategoriesListener = (AppCategoriesListener)context;
        else throw new RuntimeException(context.toString() + " must implement AppCategoriesListener");
    }


    AppCategoriesListener appCategoriesListener;
    public interface AppCategoriesListener {
        void logout();
        void appListDisplay(String category, String token);
    }

}