package com.example.bibliosiwarsoulaima.view;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;


import com.example.bibliosiwarsoulaima.controller.Register;
import com.example.bibliosiwarsoulaima.R;

import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {
    EditText sign_up_email;
    EditText sign_up_password , confirm_password;
    Button sign_up_button;
    TextView loginRedirectText;
    Register registerController = new Register(this);

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = registerController.getCurrentUser();
        if(currentUser != null && currentUser.isEmailVerified() ){
            Intent intent = new Intent(getApplicationContext() , LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        sign_up_email = findViewById(R.id.signup_email);
        sign_up_password = findViewById(R.id.signupPassword);
        sign_up_button = findViewById(R.id.signupButton);
        loginRedirectText = findViewById(R.id.loginText);
        confirm_password = findViewById(R.id.confirm_password);



        loginRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( RegisterActivity.this, LoginActivity.class );
                startActivity(intent);
                finish();
            }
        });

        sign_up_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email , password , confirm ;
                email = sign_up_email.getText().toString();
                password = sign_up_password.getText().toString();
                confirm = confirm_password.getText().toString();
                boolean ok = Boolean.TRUE;
                if (TextUtils.isEmpty(email))
                {
                    Toast.makeText(RegisterActivity.this, "Enter an email!!!", Toast.LENGTH_SHORT).show();
                    ok = Boolean.FALSE;
                }
                if (TextUtils.isEmpty(password))
                {
                    Toast.makeText(RegisterActivity.this, "Enter a password!!!", Toast.LENGTH_SHORT).show();
                    ok = Boolean.FALSE;
                }
                if (TextUtils.isEmpty(confirm))
                {
                    Toast.makeText(RegisterActivity.this, "Fill the confirmation area!!!", Toast.LENGTH_SHORT).show();
                    ok = Boolean.FALSE;
                }
                if (password.equals(confirm) && ok == Boolean.TRUE)
                {
                    registerController.createUser(email, password);
                } else if (ok == Boolean.FALSE) {
                    Toast.makeText(RegisterActivity.this, "Fill all fields please!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(RegisterActivity.this, "Password problem", Toast.LENGTH_SHORT).show();
                }



            }
    });
}

}