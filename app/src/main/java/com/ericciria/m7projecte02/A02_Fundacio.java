package com.ericciria.m7projecte02;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class A02_Fundacio extends AppCompatActivity {

    TextView textDescripcio;
    ImageButton ibText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a02_fundacio);

        textDescripcio = findViewById(R.id.tvTextInici);
        textDescripcio.setMovementMethod(new ScrollingMovementMethod());
        ibText = findViewById(R.id.ibText);

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

    public void textIdioma(MotionEvent event){
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                ibText.setColorFilter(R.color.Activat);
            case MotionEvent.ACTION_UP:
                ibText.setColorFilter(R.color.Desactivat);
        }
    }
}