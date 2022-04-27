package com.ericciria.m7projecte02;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class A04_Escultures extends AppCompatActivity {

    private RecyclerView rvEscultures;
    private RecyclerView.Adapter esculturesAdapter;
    private RecyclerView.LayoutManager esculturesLayout;
    private ArrayList<Escultura> escultures;

    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a04_escultures);

        rvEscultures = findViewById(R.id.rvEscultures);
        db = FirebaseFirestore.getInstance();


        // Si volem llista, activem el new LinearLayout...(); si volem grid, activem
        // el new GridLayout...(), indicant el nombre de columnes.
        //esculturesLayout = new LinearLayoutManager(this);
        //rvEscultures.setLayoutManager(esculturesLayout);
        rvEscultures.setLayoutManager(new GridLayoutManager(this,2));

        // Creem el model de dades que mostrarem al RecyclerView. Executem una consulta sobre
        // la base de dades i guardem els resultats en un ArrayList.
        escultures = new ArrayList<Escultura>();

        //escultures = db.collection("asd").get();

        /*// Creem l'Adapter que associarem al RecyclerView a partir de l'ArrayList amb
        // les dades.
        esculturesAdapter = new EsculturesRVAdapter(escultures);

        // Associem l'Adapter al RecyclerView.
        rvEscultures.setAdapter(esculturesAdapter);*/
    }

    @Override
    protected void onResume() {
        super.onResume();
        db.collection("escultures")
                //.document("asd1")
                .whereGreaterThan("altura",0)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Toast.makeText(A04_Escultures.this, "" + document.get("idEscultura"), Toast.LENGTH_SHORT).show();
                                Escultura novaEscultura = new Escultura(
                                        document.get("idEscultura").toString(),
                                        Double.valueOf(document.get("altura").toString()),
                                        Double.valueOf(document.get("amplada").toString()),
                                        Double.valueOf(document.get("pes").toString()),
                                        Integer.valueOf(document.get("any").toString())    ,
                                        Double.valueOf(document.get("latitud").toString()),
                                        Double.valueOf(document.get("longitud").toString())
                                );
                                escultures.add(novaEscultura);
                            }
                            esculturesAdapter = new EsculturesRVAdapter(escultures);

                            // Associem l'Adapter al RecyclerView.
                            rvEscultures.setAdapter(esculturesAdapter);
                        } else {
                            Toast.makeText(A04_Escultures.this, "No existeix", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        Toast.makeText(A04_Escultures.this, "AAA" + esculturesAdapter, Toast.LENGTH_SHORT).show();
        //Toast.makeText(A04_Escultures.this, esculturesAdapter.getItemCount(), Toast.LENGTH_SHORT).show();
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