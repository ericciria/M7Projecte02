package com.ericciria.m7projecte02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Layout;
import android.view.View;
import android.window.SplashScreen;

public class MainActivity extends AppCompatActivity {

    private static final long SPLASH_TIEMPO = 4000;
    View lTop;
    View lMid;
    View lBot;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lTop = findViewById(R.id.cTop);
        lMid = findViewById(R.id.cMiddle);
        lBot = findViewById(R.id.cBottom);

        //lTop.displa

        new Handler().postDelayed(new Runnable() {
            /*
             * Mostramos la pantalla de bienvenida con un temporizador.
             * De esta forma se puede mostrar el logo de la app o
             * compañia durante unos segundos.
             */

            @Override
            public void run() {
                // Este método se ejecuta cuando se consume el tiempo del temporizador.
                // Se pasa a la activity principal
                Intent i = new Intent(MainActivity.this, A02_Fundacio.class);
                startActivity(i);

                // Cerramos esta activity
                finish();
            }
        }, SPLASH_TIEMPO);







    }


    public void anarAMapa (View v) {
        Intent mapejar = new Intent(this, A03_Mapa.class);
        startActivity(mapejar);

    }

    public void anarAArtistes (View x) {
        Intent artistejar = new Intent(this, A05_Artistes.class);
        startActivity(artistejar);

    }

    public void anarAEscultures (View y) {

        Intent escultpturar = new Intent(this, A04_Escultures.class);
        startActivity(escultpturar);

    }

    public void anarAFundcacio (View z) {


        Intent fundar = new Intent(this, A02_Fundacio.class);
        startActivity(fundar);

    }


}