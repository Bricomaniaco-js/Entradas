package com.example.myapplication.ui.activities;

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

public class LoginFragment extends Fragment {
    Button loginButton;
    View view;
    EditText passwordEditText;
    EditText emailEditText;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_login, container, false);
        loginButton = (Button) view.findViewById(R.id.loginButton);
        passwordEditText = (EditText) view.findViewById(R.id.passwordEditText);
        emailEditText = (EditText) view.findViewById(R.id.emailEditText);
        passwordEditText.setText("password");
        emailEditText.setText("username");
        loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ((LoginActivity)getActivity()).loginButtonClick(getEmail(), getPassword());
            }
        }
        );
        return view;
    }

    private String getEmail() {
        return emailEditText.getText().toString();
    }
    private String getPassword() {
        return passwordEditText.getText().toString();
    }

}
