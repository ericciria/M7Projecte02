package com.ericciria.m7projecte02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class A05_Artistes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a05_artistes);
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