package com.softbwh.jesus.dsp2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;


public class Juego extends Activity {
    private int respuestaCorrecta;
    private int aciertos;
    private int nPreguntas;
    private TextView tvPregunta;
    private Button boton1,boton2,boton3,boton4;
    private ArrayList<Enunciado> enunciados;
    private final String categoriaJuego = "futbol";
    //se necesitara aqui tener el test generado ya

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);

        PYRDataSource datos= new PYRDataSource(this);
        //datos.getDatabase().execSQL("select * from preguntas;");

        /* Asignamos los ids a los elementos */
        tvPregunta = (TextView) findViewById(R.id.tvPregunta);
        boton1 = (Button) findViewById(R.id.button);
        boton2 = (Button) findViewById(R.id.button2);
        boton3 = (Button) findViewById(R.id.button3);
        boton4 = (Button) findViewById(R.id.button4);

        /****** CREAR EL TEST ******/

        enunciados = datos.obtenerEnunciados(categoriaJuego);






        /****** CREAR EL TEST ******/

        /* Asignamos la primera pregunta con sus respuestas de manera random se deberia asignar */
        /* Hay que comprobar de que tipo es la pregunta (grafico, audio, texto) */
        tvPregunta.setText(enunciados.get(0).getPreguntaEnunciado());
        boton1.setText(enunciados.get(0).getRespuestasEnunciados().get(0));
        boton2.setText(enunciados.get(0).getRespuestasEnunciados().get(1));
        boton3.setText(enunciados.get(0).getRespuestasEnunciados().get(2));
        boton4.setText(enunciados.get(0).getRespuestasEnunciados().get(3));
        respuestaCorrecta=1; //numero del boton de la respuesta correcta (cambia en cada pregunta)

        /* variables de control de las preguntas */
        aciertos=0; //variable que suma los aciertos de 1 en 1 (no suma nada si falla)
        nPreguntas=10; //numero de preguntas totales (soloc ambia al generar el test
        /* Poner una variable que diga cual es la respuesta correcta */

        boton1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if(respuestaCorrecta==1){
                    /* sonido correcto */
                    aciertos++;
                    /* variable de aciertos++ */
                }else{
                    /* sonido mal */
                }

                /*comprobamos si es la ultima pregunta */
                if(nPreguntas==1){
                    /* ir a FinalJuego */

                    Intent i = new Intent(Juego.this, FinalJuego.class);
                    i.putExtra("aciertos", aciertos);
                    startActivity(i);
                }else{
                    /* siguiente pregunta */
                    /* comprobar si la siguiente pregunta es grafica audio o texto */
                    tvPregunta.setText("¿Cual es la capital de España?");
                    boton1.setText("Madrid");
                    boton2.setText("Barcelona");
                    boton3.setText("Santander");
                    boton4.setText("Granada");
                }
                nPreguntas--;
            }
        });

        boton2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if(respuestaCorrecta==2){
                    /* sonido correcto */
                    aciertos++;
                }else{
                    /* sonido mal */
                }

                /*comprobamos si es la ultima pregunta */
                if(nPreguntas==1){
                    /* ir a FinalJuego */

                    Intent i = new Intent(Juego.this, FinalJuego.class);
                    i.putExtra("aciertos", aciertos);
                    startActivity(i);
                }else{
                    /* siguiente pregunta */
                    tvPregunta.setText("¿Cual es la capital de España?");
                    boton1.setText("Madrid");
                    boton2.setText("Barcelona");
                    boton3.setText("Santander");
                    boton4.setText("Granada");
                }
                nPreguntas--;
            }
        });

        boton3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if(respuestaCorrecta==3){
                    /* sonido correcto */
                    aciertos++;
                }else{
                    /* sonido mal */
                }

                /*comprobamos si es la ultima pregunta */
                if(nPreguntas==1){
                    /* ir a FinalJuego */

                    Intent i = new Intent(Juego.this, FinalJuego.class);
                    i.putExtra("aciertos", aciertos);
                    startActivity(i);
                }else{
                    /* siguiente pregunta */
                    tvPregunta.setText("¿Cual es la capital de España?");
                    boton1.setText("Madrid");
                    boton2.setText("Barcelona");
                    boton3.setText("Santander");
                    boton4.setText("Granada");
                }
                nPreguntas--;
            }
        });

        boton4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if(respuestaCorrecta==4){
                    /* sonido correcto */
                    aciertos++;
                }else{
                    /* sonido mal*/
                }

                /*comprobamos si es la ultima pregunta */
                if(nPreguntas==1){
                    /* ir a FinalJuego */

                    Intent i = new Intent(Juego.this, FinalJuego.class);
                    i.putExtra("aciertos", aciertos);
                    startActivity(i);
                }else{
                    /* siguiente pregunta */
                    tvPregunta.setText("¿Cual es la capital de España?");
                    boton1.setText("Madrid");
                    boton2.setText("Barcelona");
                    boton3.setText("Santander");
                    boton4.setText("Granada");
                }
                nPreguntas--;
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.juego, menu);
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
