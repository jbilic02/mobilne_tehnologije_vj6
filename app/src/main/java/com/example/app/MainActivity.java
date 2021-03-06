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

public class MainActivity extends AppCompatActivity {
    private Button btnSignUp;
    private EditText etEmail;
    private EditText etPassword;


    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);

        btnSignUp = findViewById(R.id.btnSignUp);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });
    }
    private void signUp(){
        String email, password;
        email = etEmail.getText().toString().trim();
        password = etPassword.getText().toString().trim();

        if(email.equals("")){
            Toast.makeText(MainActivity.this, "Unesi Email",Toast.LENGTH_SHORT).show();
        }
        else if(password.equals("")){
            Toast.makeText(MainActivity.this,"Unesi Password",Toast.LENGTH_SHORT).show();
        }
        else if(password.length() < 6){
            Toast.makeText(MainActivity.this,"Kratka Password",Toast.LENGTH_SHORT).show();
        }
        else{
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(MainActivity.this,"Uspjesna prijava",Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(MainActivity.this,SignIn.class);
                        startActivity(i);
                        finish();
                    }
                    else{
                        //text: moze se dodat da korisnik postoji
                        Toast.makeText(MainActivity.this,"Neuspjesna prijava",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }
}