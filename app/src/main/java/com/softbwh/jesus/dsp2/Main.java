package com.softbwh.jesus.dsp2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class Main extends Activity {

    //Código de envío
    public final static int ADD_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Iniciando la actividad Form
        //Intent intent = new Intent(this, java.text.Normalizer.Form.class);
        //Inicio de la actividad esperando un resultado
        //startActivityForResult(intent, ADD_REQUEST_CODE);

        Button botonJugar = (Button) findViewById(R.id.button);
        botonJugar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent i = new Intent(Main.this, Juego.class);
                startActivity(i);
            }
        });

        Button botonResultados = (Button) findViewById(R.id.button2);
        botonResultados.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent i = new Intent(Main.this, Resultados.class);
                startActivity(i);
            }
        });

        Button botonOtrosJuegos = (Button) findViewById(R.id.button3);
        botonOtrosJuegos.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent i = new Intent(Main.this, OtrosJuegos.class);
                startActivity(i);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
