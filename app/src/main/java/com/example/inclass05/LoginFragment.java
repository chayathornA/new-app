package com.example.inclass05;
/*
Assignment: HW03
File Name: LoginFragment.java
Students (Group 14): Tyler Johnson, Chayathorn Akramongkolrojn
 */
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment# newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {

    //UI components
    private EditText email, password;


    public LoginFragment() {
        // Required empty public constructor
    }

    public static LoginFragment newInstance () {
        LoginFragment frag = new LoginFragment();
        return frag;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        email = view.findViewById(R.id.editTextLoginEmail);
        password = view.findViewById(R.id.editTextLoginPassword);

        view.findViewById(R.id.buttonLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginListener.login(email.getText().toString(), password.getText().toString());
            }
        });

        view.findViewById(R.id.textViewLoginNewAccount).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginListener.createNewAccount();
            }
        });

        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof LoginInterface) loginListener = (LoginInterface)context;
        else throw new RuntimeException(context.toString() + " must implement LoginInterface");
    }

    LoginInterface loginListener;
    public interface LoginInterface {
        void login(String email, String password);
        void createNewAccount();
    }
}