package com.example.inclass05;
/*
Assignment: HW03
File Name: MainActivity.java
Students (Group 14): Tyler Johnson, Chayathorn Akramongkolrojn
 */
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements LoginFragment.LoginInterface,
RegisterFragment.RegisterListener, AppCategoriesFragment.AppCategoriesListener, AppListFragment.AppListListener,
        AppDetailsFragment.AppDetailsListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Login");

        //creates and display login fragment
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.mainLayout, LoginFragment.newInstance(), "login-fragment")
                .commit();
    }

    //LoginInterface methods
    @Override
    public void login(String email, String password) {
        DataServices.login(email, password, new DataServices.AuthResponse() {
            @Override
            public void onSuccess(String token) {
                setTitle("App Categories");
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.mainLayout, AppCategoriesFragment.newInstance(token), "app-categories-fragment")
                        .commit();
            }

            @Override
            public void onFailure(DataServices.RequestException exception) {
                Toast.makeText(MainActivity.this, exception.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void createNewAccount() {
        setTitle("Register Account");
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.mainLayout, RegisterFragment.newInstance(), "register-fragment")
                .commit();
    }

    //RegisterInterface methods
    @Override
    public void registerAccount(String name, String email, String password) {
        DataServices.register(name, email, password, new DataServices.AuthResponse() {
            @Override
            public void onSuccess(String token) {
                setTitle("App Categories");
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.mainLayout, AppCategoriesFragment.newInstance(token), "app-categories-fragment")
                        .commit();
            }

            @Override
            public void onFailure(DataServices.RequestException exception) {
                Toast.makeText(MainActivity.this, exception.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void cancelRegistration() {
        setTitle("Login");
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.mainLayout, LoginFragment.newInstance(), "register-fragment")
                .commit();
    }

    //AppCategoriesListener methods
    @Override
    public void logout() {
        setTitle("Login");
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.mainLayout, LoginFragment.newInstance(), "register-fragment")
                .commit();
    }

    @Override
    public void appListDisplay(String category, String token) {
        setTitle(category);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.mainLayout, AppListFragment.newInstance(category, token), "register-fragment")
                .addToBackStack(null)
                .commit();
    }


    //AppListListener methods
    @Override
    public void closeAppList() {
        setTitle("App Categories");
    }

    @Override
    public void openAppDetails(String userToken, DataServices.App app, String category) {
        setTitle("App Details");
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.mainLayout, AppDetailsFragment.newInstance(userToken, app, category), "register-fragment")
                .addToBackStack(null)
                .commit();
    }

    //AppDetailsListener methods
    @Override
    public void closeAppDetails(String category) {
        setTitle(category);
    }
}