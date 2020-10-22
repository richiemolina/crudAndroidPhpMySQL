package com.example.crudproyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
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

public class Editar extends AppCompatActivity {

    EditText edId,edName,edContact,edEmail;
    Button btn1;
    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);


        edId = findViewById(R.id.ed_id);
        edName = findViewById(R.id.ed_name);
        edContact = findViewById(R.id.ed_contact);
        edEmail = findViewById(R.id.ed_email);

        Intent intent = getIntent();
        position =intent.getExtras().getInt("position");
        edId.setText(Home.users.get(position).getId());
        edName.setText(Home.users.get(position).getNombre());
        edEmail.setText(Home.users.get(position).getEmail());
        edContact.setText(Home.users.get(position).getContacto());

    }
    public void actualizar(View view){
        final String nombre = edName.getText().toString();
        final String email = edEmail.getText().toString();
        final String contacto = edContact.getText().toString();
        final String id = edId.getText().toString();





        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Cargando....");
        progressDialog.show();

        StringRequest request = new StringRequest(Request.Method.POST, "https://appproyectocrud.000webhostapp.com/crud/update.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Toast.makeText(Editar.this, response, Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),Home.class));
                        finish();
                        progressDialog.dismiss();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(Editar.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();

            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> params = new HashMap<String,String>();

                params.put("id",id);
                params.put("nombre",nombre);
                params.put("email",email);
                params.put("contacto",contacto);


                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(Editar.this);
        requestQueue.add(request);
    }
}