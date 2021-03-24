package com.example.inclass05;
/*
Assignment: HW03
File Name: AppListFragment.java
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
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class AppListFragment extends Fragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1_CATEGORY = "param1";
    private static final String ARG_PARAM2_TOKEN = "param2";

    private String category;
    private String token;
    RecyclerView appList;
    ArrayList<DataServices.App> appData;
    LinearLayoutManager linearLayoutManager;
    AppListAdapter adapter;

    public AppListFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static AppListFragment newInstance(String param1, String param2) {
        AppListFragment fragment = new AppListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1_CATEGORY, param1);
        args.putString(ARG_PARAM2_TOKEN, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            category = getArguments().getString(ARG_PARAM1_CATEGORY);
            token = getArguments().getString(ARG_PARAM2_TOKEN);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_app_list, container, false);

        //imports app data
        DataServices.getAppsByCategory(token, category, new DataServices.DataResponse<DataServices.App>() {
            @Override
            public void onSuccess(ArrayList<DataServices.App> data) {
                appData = data;
                Log.d("myApp", "AppList data retrieval successful");
            }

            @Override
            public void onFailure(DataServices.RequestException exception) {
                Toast.makeText(getContext(), exception.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        appList = view.findViewById(R.id.recyclerViewAppList);
        adapter = new AppListAdapter(appData, token, category);
        linearLayoutManager = new LinearLayoutManager(getContext());
        appList.setLayoutManager(linearLayoutManager);
        appList.setAdapter(adapter);


        return view;
    }

    @Override
    public void onDestroy() {
        appListListener.closeAppList();
        super.onDestroy();
    }

    AppListListener appListListener;
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof AppListListener) appListListener = (AppListListener)context;
        else throw new RuntimeException(context.toString() + " must be an instance of AppListListener");
    }


    public interface AppListListener {
        void closeAppList();
        void openAppDetails(String userToken, DataServices.App app, String category);
    }
}