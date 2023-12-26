package com.example.turisticos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

import WebService.Asynchtask;
import WebService.WebService;

public class MainActivity extends AppCompatActivity implements Asynchtask {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void clickLogin(View v){

        Bundle bundle = this.getIntent().getExtras();
        Map<String, String> datos = new HashMap<String, String>();
        EditText txtusuario = findViewById(R.id.txtusuario);
        EditText txtclave = findViewById(R.id.txtclave);
        WebService ws= new WebService(
                "https://revistas.uteq.edu.ec/ws/login.php?usr="
                        + txtusuario.getText().toString() +
                        "&pass=" + txtclave.getText().toString(),
                datos,
                MainActivity.this, MainActivity.this);
        ws.execute("GET");

    }

    @Override
    public void processFinish(String result) throws JSONException {
        enviar(result);
    }

    private void enviar(String info) {
        // Crear un Intent para abrir la otra actividad
        Intent intent = new Intent(this, MainActivity2.class);

        // Agregar la información como un extra en el Intent
        intent.putExtra("RESULTADO_EXTRA", info);

        // Iniciar la otra actividad
        startActivity(intent);
    }
}