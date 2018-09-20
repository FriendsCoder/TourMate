package com.example.sabuj.tourmate;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.sabuj.tourmate.fragments.LoginFragment;
import com.example.sabuj.tourmate.fragments.SignUpFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginRegisterActivity extends AppCompatActivity {
    TextView btnLogin, btnSignup;
    FragmentTransaction transaction;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        if (firebaseUser != null) {
            finish();
            startActivity(new Intent(LoginRegisterActivity.this, MainActivity.class));
        }
        setContentView(R.layout.activity_login_register);
        btnLogin = findViewById(R.id.btnLogin);
        btnSignup = findViewById(R.id.btnSignup);

        btnLogin.callOnClick();
        loginMethod();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginMethod();
            }
        });
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignUpFragment signUpFragment = new SignUpFragment();
                transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.loginRegisterframeLayout, signUpFragment);
                btnLogin.setBackgroundResource(R.drawable.button_background);
                btnSignup.setBackgroundResource(R.drawable.button_background_selected);
                transaction.commit();
            }
        });

    }

    private void loginMethod() {
        LoginFragment loginFragment = new LoginFragment();
        transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.loginRegisterframeLayout, loginFragment);
        btnSignup.setBackgroundResource(R.drawable.button_background);
        btnLogin.setBackgroundResource(R.drawable.button_background_selected);
        transaction.commit();
    }
}
