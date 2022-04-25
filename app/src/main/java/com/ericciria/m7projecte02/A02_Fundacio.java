package com.ericciria.m7projecte02;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.Blob;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class A02_Fundacio extends AppCompatActivity {

    TextView textDescripcio;
    ImageButton ibText;


    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a02_fundacio);

        textDescripcio = findViewById(R.id.tvTextInici);
        textDescripcio.setMovementMethod(new ScrollingMovementMethod());
        ibText = findViewById(R.id.ibText);

        String uids = "";
        // Obtenim una instància d'accés a la base de dades Firestore.
        db = FirebaseFirestore.getInstance();
        Toast.makeText(A02_Fundacio.this, "" + db.collection("asd").get(), Toast.LENGTH_SHORT).show();
        //Toast.makeText(A02_Fundacio.this, "" + db.collection("asd").document("asd1"), Toast.LENGTH_SHORT).show();

        db.collection("asd")
                .document("asd1")
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    Toast.makeText(A02_Fundacio.this, ""+ documentSnapshot.getData(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(A02_Fundacio.this, "No existeix", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                e.printStackTrace();
                Toast.makeText(A02_Fundacio.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });

        ibText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, String> biografia = null;
                Map<String, String> correntArtistic = null;
                Map<String, Blob[]> audio = null;
                Blob[] foto = null;

                // Codi que realitzarà unes quantes insercins d'artistes.
                List<Artista> artista = Arrays.asList(
                        new Artista("01", "Artista01", "Cognom01", 2000, -1, biografia, correntArtistic, audio, foto),
                        new Artista("02", "Artista02", "Cognom02", 2000, -1, biografia, correntArtistic, audio, foto),
                        new Artista("03", "Artista03", "Cognom03", 2000, -1, biografia, correntArtistic, audio, foto),
                        new Artista("04", "Artista04", "Cognom04", 2000, -1, biografia, correntArtistic, audio, foto),
                        new Artista("05", "Artista05", "Cognom05", 2000, -1, biografia, correntArtistic, audio, foto)
                );

                // Bucle que fa la inserció dels artistes.
                for (Artista a: artista) {
                    db.collection("artistes")
                            .document(a.getIdArtista())
                            .set(a)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void v) {
                                    // En cas que la inserció hagi anat bé, no farem res en especial.
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                                        @Override
                                                        public void onFailure(@NonNull Exception e) {
                                                            Toast.makeText(A02_Fundacio.this, "La inserció ha fallat: " + e.getMessage(), Toast.LENGTH_LONG).show();
                                                        }
                                                    }
                    );
                }

                Map<String, String> nom = null;
                Map<String, String> material = null;
                Map<String, Blob[]> audio2 = null;
                List<Blob[]> imatges = null;

                // Codi que realitzarà unes quantes insercins d'escultures.
                List<Escultura> escultura = Arrays.asList(
                        new Escultura("01", nom, material, 25.0, 10.0, 500.0, 2005, audio2, imatges, 25.0, 25.0),
                        new Escultura("02", nom, material, 25.0, 10.0, 500.0, 2005, audio2, imatges, 30.0, 25.0),
                        new Escultura("03", nom, material, 25.0, 10.0, 500.0, 2005, audio2, imatges, 25.0, 30.0),
                        new Escultura("04", nom, material, 25.0, 10.0, 500.0, 2005, audio2, imatges, 40.0, 25.0),
                        new Escultura("05", nom, material, 25.0, 10.0, 500.0, 2005, audio2, imatges, 25.0, 40.0)
                );

                // Bucle que fa la inserció dels artistes.
                for (Escultura a: escultura) {
                    db.collection("escultures")
                            .document(a.getIdEscultura())
                            .set(a)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void v) {
                                    // En cas que la inserció hagi anat bé, no farem res en especial.
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                                        @Override
                                                        public void onFailure(@NonNull Exception e) {
                                                            Toast.makeText(A02_Fundacio.this, "La inserció ha fallat: " + e.getMessage(), Toast.LENGTH_LONG).show();
                                                        }
                                                    }
                    );
                }
            }
        });
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