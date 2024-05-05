package com.example.bibliosiwarsoulaima.view;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.bibliosiwarsoulaima.controller.Login;
import com.example.bibliosiwarsoulaima.R;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    EditText login_email;
    EditText login_password;
    Button login_button;
    TextView signupRedirectText;

    Login loginController = new Login(this);

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = loginController.getCurrentUser();
        if (currentUser != null && currentUser.isEmailVerified()) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login_email = findViewById(R.id.loginEmail);
        login_password = findViewById(R.id.loginPassword);
        login_button = findViewById(R.id.loginButton);
        signupRedirectText = findViewById(R.id.signupText);


        //moving from login activity to the Register activity
        signupRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //login user
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email, password;
                email = login_email.getText().toString();
                password = login_password.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(LoginActivity.this, "Enter an email!!!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(LoginActivity.this, "Enter a password!!!", Toast.LENGTH_SHORT).show();
                    return;
                }

                loginController.signUp(email,password);


            }
        });
    }
}

