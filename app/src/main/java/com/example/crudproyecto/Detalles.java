package com.example.crudproyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class Detalles extends AppCompatActivity {

    TextView tvid,tvname,tvemail,tvcontact,tvaddress;
    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles);

        tvid = findViewById(R.id.txtid);
        tvname = findViewById(R.id.txtname);
        tvemail = findViewById(R.id.txtemail);
        tvcontact = findViewById(R.id.txcontact);


        Intent intent = getIntent();
        position = intent.getExtras().getInt("position");

        tvid.setText("ID --> "+Home.users.get(position).getId());
        tvname.setText("Nombre --> "+Home.users.get(position).getNombre());
        tvemail.setText("Email --> "+Home.users.get(position).getEmail());
        tvcontact.setText("Contacto --> "+Home.users.get(position).getContacto());


    }
}