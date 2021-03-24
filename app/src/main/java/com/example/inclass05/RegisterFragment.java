package com.example.inclass05;
/*
Assignment: HW03
File Name: RegisterFragment.java
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
 * Use the {@link RegisterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegisterFragment extends Fragment {

    EditText name, email, password;

    public RegisterFragment() {
        // Required empty public constructor
    }
    // TODO: Rename and change types and number of parameters
    public static RegisterFragment newInstance() {
        RegisterFragment fragment = new RegisterFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        //initializes UI elements
        name = view.findViewById(R.id.editTextRegisterName);
        email = view.findViewById(R.id.editTextRegisterEmail);
        password = view.findViewById(R.id.editTextRegisterPassword);

        //adds cancel button functionality
        view.findViewById(R.id.textViewRegisterCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerListener.cancelRegistration();
            }
        });

        //adds submit button functionality
        view.findViewById(R.id.buttonRegisterSubmit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerListener.registerAccount(name.getText().toString(),
                                                email.getText().toString(),
                                                password.getText().toString());
            }
        });

        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof RegisterFragment.RegisterListener) registerListener = (RegisterFragment.RegisterListener)context;
        else throw new RuntimeException(context.toString() + " must implement RegisterListener");
    }

    RegisterListener registerListener;
    public interface RegisterListener {
        void registerAccount(String name, String email, String password);
        void cancelRegistration();
    }
}