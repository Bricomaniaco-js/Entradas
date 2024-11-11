package com.example.myapplication.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.App;
import com.example.myapplication.R;
import com.example.myapplication.UserManager.UserManager;


public class LoginActivity extends AppCompatActivity {
    //UserManager um;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //um = App.getInstance().getUserManager();

        showLoginFragment();
    }

    private void showLoginFragment(){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.login_container, new LoginFragment())
                .commit();
    }
    public void loginButtonClick(String mail, String password){
        Toast.makeText(this, "Logged in", Toast.LENGTH_SHORT).show();
        navigateToHomePage();
        /*
        Toast.makeText(this, "Logging in..", Toast.LENGTH_SHORT).show();
        if(um.login(mail, password)){
            Toast.makeText(this, "Logged in", Toast.LENGTH_SHORT).show();
            navigateToHomePage();
        }else{
            Toast.makeText(this, "Invalid credentials", Toast.LENGTH_SHORT).show();
        };
*/
    }
    private void navigateToHomePage(){
        Intent i = new Intent(this, HomeActivity.class);
        startActivity(i);
    }
}
