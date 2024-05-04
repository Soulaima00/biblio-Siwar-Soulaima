package com.example.bibliosiwarsoulaima.controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.util.Log;

import android.widget.Toast;

import com.example.bibliosiwarsoulaima.view.LoginActivity;
import com.example.bibliosiwarsoulaima.view.RegisterActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Register extends AppCompatActivity {

    FirebaseAuth mAuth;
    RegisterActivity registerActivity;

    public Register(RegisterActivity registerActivity){
        mAuth = FirebaseAuth.getInstance();
        this.registerActivity = registerActivity;
    }
    public FirebaseUser getCurrentUser(){
        return mAuth.getCurrentUser();
    }

    public void createUser(String email, String password){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            FirebaseUser user = mAuth.getCurrentUser();
                            if (user != null) {
                                Log.i("soulaima","here 0");
                                user.sendEmailVerification()
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                task.isSuccessful();
                                            }
                                        });
                            }
                            if (getCurrentUser().isEmailVerified()) {
                                Intent intent = new Intent(registerActivity.getApplicationContext(), LoginActivity.class);
                                registerActivity.startActivity(intent);
                                registerActivity.finish();
                            }
                            else{
                                Toast.makeText(registerActivity , "Verify your email then Login",Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(registerActivity , "you have to verify mail or password must be more 6", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

}

}