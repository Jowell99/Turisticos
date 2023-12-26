package com.example.turisticos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import WebService.Asynchtask;
import WebService.WebService;

public class MainActivity3 extends AppCompatActivity {

    private TextView txtListaBancos;
    private RequestQueue requestQueue;
    private final String url = "https://uealecpeterson.net/turismo/lugar_turistico/json_getlistadoGrid";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtListaBancos = findViewById(R.id.txtListaBancos);
        requestQueue = Volley.newRequestQueue(this);

        obtenerDatos();
    }

    private void obtenerDatos() {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // Procesar la respuesta JSON
                        try {
                            StringBuilder stringBuilder = new StringBuilder();
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject lugar = response.getJSONObject(i);
                                String categoria = lugar.getString("categoria");
                                String nombreLugar = lugar.getString("nombre_lugar");
                                String telefono = lugar.getString("telefono");

                                // Agregar los datos al StringBuilder
                                stringBuilder.append("Categoría: ").append(categoria).append("\n");
                                stringBuilder.append("Nombre: ").append(nombreLugar).append("\n");
                                stringBuilder.append("Teléfono: ").append(telefono).append("\n\n");
                            }
                            // Mostrar los datos en el TextView
                            txtListaBancos.setText(stringBuilder.toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Manejar errores de la solicitud
                        txtListaBancos.setText("Error al obtener los datos");
                    }
                });

        // Agregar la solicitud a la cola
        requestQueue.add(jsonArrayRequest);
    }
}