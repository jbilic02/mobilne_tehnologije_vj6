package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class updateData extends AppCompatActivity {

    EditText etName, etInt, etPredavac;
    Button btnUpdate;
    String id,godina,ime,predavac;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_data);

        etInt = findViewById(R.id.etInt);
        etName = findViewById(R.id.etName);
        etPredavac = findViewById(R.id.etPredavac);

        btnUpdate = findViewById(R.id.btnUpdate);

        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        godina = intent.getStringExtra("godina");
        ime = intent.getStringExtra("ime");
        predavac = intent.getStringExtra("predavac");

        etInt.setText(godina);
        etName.setText(ime);
        etPredavac.setText(predavac);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get a reference to the Firebase Database
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("User Data").child(id);
                String uIme, uInt,uPredavac;

                uIme = etName.getText().toString();
                uInt = etInt.getText().toString();
                uPredavac = etPredavac.getText().toString();

                UserData userData = new UserData(id,uIme,uInt,uPredavac);
                databaseReference.setValue(userData);
                Toast.makeText(updateData.this,"Updated",Toast.LENGTH_SHORT).show();
            }
        });
    }
}