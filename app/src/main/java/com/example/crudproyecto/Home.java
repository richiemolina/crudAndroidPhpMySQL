package com.example.crudproyecto;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.crudproyecto.Users;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Home extends AppCompatActivity {

    ListView list;
    Adaptador adaptador;
    public  static ArrayList<Users> users = new ArrayList<>();
    Users usuario;

    String url = "https://appproyectocrud.000webhostapp.com/crud/retrieve.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        list = findViewById(R.id.myListView);
        adaptador = new Adaptador(this, users);
        list.setAdapter(adaptador);


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                ProgressDialog progressDialog = new ProgressDialog(view.getContext());

                CharSequence[] dialogItem = {"Mostrar datos","Editar datos","Eliminar dato"};
                builder.setTitle(users.get(position).getNombre());
                builder.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        switch (i){
                            case 0:
                                startActivity(new Intent(getApplicationContext(),Detalles.class)
                                .putExtra("position",position));
                                break;
                            case 1:
                                startActivity(new Intent(getApplicationContext(),Editar.class)
                                        .putExtra("position",position));

                                break;
                            case 2:
                                deleteData(users.get(position).getId());
                                break;

                        }
                    }
                });
                builder.create().show();
            }
        });
        mostrarDatos();

    }

    public void mostrarDatos() {
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                users.clear();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");

                    JSONArray jsonArray = jsonObject.getJSONArray("datos");
                    if (success.equals("1")) {
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject object = jsonArray.getJSONObject(i);

                            String id = object.getString("id");
                            String nombre = object.getString("nombre");
                            String email = object.getString("email");
                            String contacto = object.getString("contacto");

                            usuario = new Users(id, nombre, email, contacto);
                            users.add(usuario);
                            adaptador.notifyDataSetChanged();

                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Home.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                //progressDialog.dismiss();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }
    public void btnAgg(View view){

        startActivity(new Intent(getApplicationContext(),insertar.class));

    }



    private void deleteData(final String id) {

        StringRequest request = new StringRequest(Request.Method.POST, "https://appproyectocrud.000webhostapp.com/crud/delete.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if(response.equalsIgnoreCase("Eliminar dato")){
                            Toast.makeText(Home.this, "Datos eliminados correctamente", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(Home.this, "Datos eliminados", Toast.LENGTH_SHORT).show();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Home.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> params = new HashMap<String,String>();
                params.put("id", id);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);


    }
}