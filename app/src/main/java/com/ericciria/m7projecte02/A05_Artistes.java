package com.ericciria.m7projecte02;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.Blob;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;


public class A05_Artistes extends AppCompatActivity {


    private RecyclerView rvArtistes;
    private RecyclerView.Adapter artistesesAdapter;
    private RecyclerView.LayoutManager artistesLayout;
    private ArrayList<Artista> artista;


    FirebaseFirestore db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a05_artistes);


        rvArtistes = findViewById(R.id.rvArtistes);
        db = FirebaseFirestore.getInstance();

        // Si volem llista, activem el new LinearLayout...(); si volem grid, activem
        // el new GridLayout...(), indicant el nombre de columnes.
        artistesLayout = new LinearLayoutManager(this);
        //rvEscultures.setLayoutManager(esculturesLayout);
        rvArtistes.setLayoutManager(artistesLayout);

        // Creem el model de dades que mostrarem al RecyclerView. Executem una consulta sobre
        // la base de dades i guardem els resultats en un ArrayList.
        artista = new ArrayList<Artista>();

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
        db.collection("artistes")
                //.document("asd1")
                .whereGreaterThan("anyNaixement",0)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                //Toast.makeText(A05_Artistes.this, "" + document.get("idArtista"), Toast.LENGTH_SHORT).show();
                                //Toast.makeText(A05_Artistes.this, "" + document.get("foto"), Toast.LENGTH_SHORT).show();
                                Artista nouArtista = new Artista(
                                        document.get("idArtista").toString(),
                                        document.get("nom").toString(),
                                        document.get("cognoms").toString(),
                                        Integer.valueOf(document.get("anyNaixement").toString()),
                                        Integer.valueOf(document.get("anyDefuncio").toString()),
                                        (Map<String, String>) document.get("biografia"),
                                        (Map<String, String>)document.get("correntArtistic"),
                                        (Map<String, String>)document.get("audio"),
                                        (Blob) document.get("foto")
                                        );
                                artista.add(nouArtista);
                                //Toast.makeText(A05_Artistes.this, "" + nouArtista.getFoto(), Toast.LENGTH_SHORT).show();
                            }
                            artistesesAdapter = new ArtistaRVAdapter(artista);
                            // Associem l'Adapter al RecyclerView.
                            rvArtistes.setAdapter(artistesesAdapter);

                        } else {
                            Toast.makeText(A05_Artistes.this, "No existeix", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
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