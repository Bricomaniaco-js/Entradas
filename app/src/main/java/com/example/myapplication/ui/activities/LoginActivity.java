package com.example.myapplication.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.App;
import com.example.myapplication.R;
import com.example.myapplication.UserManager.UserManager;
import com.example.myapplication.model.User;

/**
 * Activity for handling user login.
 */
public class LoginActivity extends AppCompatActivity {
    private UserManager um;

    /**
     * Called when the activity is first created.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down then this Bundle contains the data it most recently supplied in onSaveInstanceState(Bundle). Otherwise it is null.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        um = App.getInstance().getUserManager();

        showLoginFragment();
    }

    /**
     * Displays the login fragment.
     */
    private void showLoginFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.login_container, new LoginFragment())
                .commit();
    }

    /**
     * Handles the login button click event.
     *
     * @param mail The user's email.
     * @param password The user's password.
     */
    public void loginButtonClick(String mail, String password) {
        Toast.makeText(this, "Logging in..", Toast.LENGTH_SHORT).show();
        um.login(mail, password, new UserManager.LoginCallback() {
            @Override
            public void onSuccess(User user) {
                Toast.makeText(LoginActivity.this, "Logged in", Toast.LENGTH_SHORT).show();
                if (user.isAdmin()) {
                    navigateToAdminPage();
                } else {
                    navigateToHomePage();
                }
            }

            @Override
            public void onFailure() {
                Toast.makeText(LoginActivity.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Navigates to the admin page.
     */
    private void navigateToAdminPage() {
        Intent intent = new Intent(this, AdminActivity.class);
        startActivity(intent);
    }

    /**
     * Navigates to the home page.
     */
    private void navigateToHomePage() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}