package com.example.crudproyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class insertar extends AppCompatActivity {

    EditText tvnombre,tvemail,tvcontacto;
    Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        tvnombre = findViewById(R.id.tvnombre);
        tvemail = findViewById(R.id.tvemail);
        tvcontacto = findViewById(R.id.tvcontacto);

        btn1 = findViewById(R.id.btninsertar);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertar();
            }
        });



    }

    private void insertar(){
        String nombre = tvnombre.getText().toString().trim();
        String email = tvemail.getText().toString().trim();
        String contacto = tvcontacto.getText().toString().trim();


        ProgressDialog progressDialog = new ProgressDialog(this);
        if(nombre.isEmpty()){
            tvnombre.setError("Los campos deben ser obligatorios");
        }
        else if(email.isEmpty()){
            tvemail.setError("Los campos deben ser obligatorios");
        }
        else if(contacto.isEmpty()){
            tvcontacto.setError("Los campos deben ser obligatorios");
        }
        else{
            progressDialog.show();
            StringRequest request = new StringRequest(Request.Method.POST, "https://appproyectocrud.000webhostapp.com/crud/insert.php", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (response.equalsIgnoreCase("Datos almacenados en la base de datos")) {
                        Toast.makeText(insertar.this, "Datos almacenados en la base de datos", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();

                    } else {
                        Toast.makeText(insertar.this, response, Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(insertar.this,error.getMessage(),Toast.LENGTH_SHORT).show();
                    Log.d("error",error.getMessage());
                    //progressDialog.dismiss();
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("nombre",nombre);
                    params.put("email",email);
                    params.put("contacto",contacto);


                    return params;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(insertar.this);
            requestQueue.add(request);

        }


    }
    public void showHome(View view){
        startActivity(new Intent(getApplicationContext(),Home.class));
    }
}