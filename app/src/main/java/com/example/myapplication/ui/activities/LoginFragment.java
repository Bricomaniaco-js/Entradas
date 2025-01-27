package com.example.myapplication.ui.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;

/**
 * Fragment for handling user login.
 */
public class LoginFragment extends Fragment {
    private Button loginButton;
    private View view;
    private EditText passwordEditText;
    private EditText emailEditText;
    private SharedPreferences sharedPreferences;

    /**
     * Called to have the fragment instantiate its user interface view.
     *
     * @param inflater The LayoutInflater object that can be used to inflate any views in the fragment.
     * @param container If non-null, this is the parent view that the fragment's UI should be attached to. The fragment should not add the view itself, but this can be used to generate the LayoutParams of the view.
     * @param savedInstanceState If non-null, this fragment is being re-constructed from a previous saved state as given here.
     * @return Return the View for the fragment's UI, or null.
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_login, container, false);
        loginButton = view.findViewById(R.id.loginButton);
        passwordEditText = view.findViewById(R.id.passwordEditText);
        emailEditText = view.findViewById(R.id.emailEditText);

        sharedPreferences = getActivity().getSharedPreferences("LoginPrefs", Context.MODE_PRIVATE);
        loadSavedCredentials();

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveCredentials(getEmail(), getPassword());
                ((LoginActivity) getActivity()).loginButtonClick(getEmail(), getPassword());
            }
        });

        return view;
    }

    /**
     * Retrieves the email entered by the user.
     *
     * @return The email entered by the user.
     */
    private String getEmail() {
        return emailEditText.getText().toString();
    }

    /**
     * Retrieves the password entered by the user.
     *
     * @return The password entered by the user.
     */
    private String getPassword() {
        return passwordEditText.getText().toString();
    }

    /**
     * Saves the email and password to SharedPreferences.
     *
     * @param email The email to save.
     * @param password The password to save.
     */
    private void saveCredentials(String email, String password) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("email", email);
        editor.putString("password", password);
        editor.apply();
    }

    /**
     * Loads the saved email and password from SharedPreferences.
     */
    private void loadSavedCredentials() {
        String email = sharedPreferences.getString("email", "");
        String password = sharedPreferences.getString("password", "");
        emailEditText.setText(email);
        passwordEditText.setText(password);
    }
}