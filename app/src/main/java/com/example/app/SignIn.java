package com.example.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignIn extends AppCompatActivity {
    private Button btnSignin;
    private EditText etEmail;
    private EditText etPassword;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_i_n);

        mAuth = FirebaseAuth.getInstance();
        etEmail = findViewById(R.id.etEmail);
        etPassword  = findViewById(R.id.etPassword);

        btnSignin = findViewById(R.id.btnSignIn);

        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });
    }
    private void signIn(){
        String email,password;
        email = etEmail.getText().toString().trim();
        password = etPassword.getText().toString().trim();

        if(email.equals("")){
            Toast.makeText(SignIn.this,"potreban je email",Toast.LENGTH_SHORT).show();
        }else if(password.equals("")){
            Toast.makeText(SignIn.this,"potreban je password",Toast.LENGTH_SHORT).show();
        }else if(password.length() < 6) {
            Toast.makeText(SignIn.this, "kratka password", Toast.LENGTH_SHORT).show();
        }
        else{
            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Intent i = new Intent(SignIn.this,Home.class);
                        startActivity(i);
                        finish();
                    }else
                    {
                        Toast.makeText(SignIn.this,"greska",Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }
    }
}