package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SaveData extends AppCompatActivity {
    private EditText godina, ime, predavac;
    private Button btnInsert;

    DatabaseReference database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_data);

        //id = findViewById(R.id.etUserId);
        godina = findViewById(R.id.etInt);
        ime = findViewById(R.id.etName);
        predavac = findViewById(R.id.etPredavac);

        btnInsert = findViewById(R.id.btnInsertData);
        // Get a reference to the Firebase Database
        database = FirebaseDatabase.getInstance().getReference().child("User Data");

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userId,userIme,userGodina,userPredavac;
                userId = database.push().getKey();

                userGodina = godina.getText().toString();
                userIme = ime.getText().toString();
                userPredavac = predavac.getText().toString();

                if(userGodina.equals("")){
                    Toast.makeText(SaveData.this,"Unesi godinu",Toast.LENGTH_SHORT).show();
                }else if(userIme.equals("")) {
                    Toast.makeText(SaveData.this, "Unesi predmet", Toast.LENGTH_SHORT).show();
                }else if(userPredavac.equals("")) {
                    Toast.makeText(SaveData.this, "Unesi predavaca", Toast.LENGTH_SHORT).show();
                }else{
                    UserData userData = new UserData(userId,userIme,userGodina,userPredavac);
                    database.child(userId).setValue(userData);
                    Toast.makeText(SaveData.this,"Podatci uneseni",Toast.LENGTH_SHORT).show();

                }

            }
        });
    }
}