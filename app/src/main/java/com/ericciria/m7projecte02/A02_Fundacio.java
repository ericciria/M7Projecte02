package com.ericciria.m7projecte02;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
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
        //Toast.makeText(A02_Fundacio.this, "" + db.collection("asd").get(), Toast.LENGTH_SHORT).show();
        //Toast.makeText(A02_Fundacio.this, "" + db.collection("asd").document("asd1"), Toast.LENGTH_SHORT).show();

        ibText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, String> biografia = new HashMap<String, String>() {{
                    put("a", "b");
                    put("c", "d");
                }};
                Map<String, String> correntArtistic = new HashMap<String, String>() {{
                    put("a", "b");
                    put("c", "d");
                }};
                Map<String, String> audio = new HashMap<String, String>() {{
                    put("a", "b");
                    put("c", "d");
                }};
                Blob foto = null;

                //Toast.makeText(A02_Fundacio.this, "" + , Toast.LENGTH_SHORT).show();

                // Codi que realitzarà unes quantes insercins d'artistes.
                Artista art = new Artista("01", "Artista01", "Cognom01", 2000, -1, biografia, correntArtistic, audio, foto);

                art.getBiografia().put("ca",
                        "Nascut a Valls (l’Alt Camp) el 1931, prengué contacte amb l’escultura a les " +
                                "classes d’art de l’Escola del Treball de la seva vila natal; després d’uns primers " +
                                "anys de cursar dibuix, pintura i escultura amb professors locals, els " +
                                "primers aprenentatges en l’escultura li arribaren de les mans del mestre " +
                                "escultor Josep Busquets, al costat del qual treballà durant 7 anys en la restauració " +
                                "del retaule major de Sant Joan de Valls, època en què s’integrà en " +
                                "el grup Un nus, format per escultors de l’Alt Camp.\n" +
                                "Participà en nombroses exposicions, fins que l’any 1954 es traslladà a Barcelona, " +
                                "sempre sota l’empara del seu mestre, i completà la seva formació " +
                                "artística en el Cercle Artístic de Sant Lluc. Morí a Valls el 2017.");
                art.getCorrentArtistic().put("ca",
                        "El seu estil, que partia del figurativisme classicitzant, propi de retaules i " +
                                "imatges religioses, anà evolucionant a la recerca de l’intent de plasmar " +
                                "el moviment a les seves escultures, bastants de les quals estan clarament " +
                                "dins del concepte de l’escultura cinegètica.");

                Log.i("AppEscultures", "001: arriba");
                art.setFoto(obtenirAssetAsBlob(getDrawable(R.drawable.logomuseu)));
                Log.i("AppEscultures", "002: arriba");
                art.getAudio().put("ca", "audio/aud01.ballantonades.mp3");

                List<Artista> artista = Arrays.asList(
                        art,
                        new Artista("02", "Artista02", "Cognom02", 2000, -1, biografia, correntArtistic, audio, foto),
                        new Artista("03", "Artista03", "Cognom03", 1980, -1, biografia, correntArtistic, audio, foto),
                        new Artista("04", "Artista04", "Cognom04", 2002, -1, biografia, correntArtistic, audio, foto),
                        new Artista("05", "Artista05", "Cognom05", 1968, -1, biografia, correntArtistic, audio, foto)
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
                        new Escultura("01", nom, material, 25.0, 10.0, 500.0, 2005, audio2, imatges, 41.758934, 2.010258),
                        new Escultura("02", nom, material, 25.0, 10.0, 500.0, 2005, audio2, imatges, 41.76002, 2.012338),
                        new Escultura("03", nom, material, 25.0, 10.0, 500.0, 2005, audio2, imatges, 41.761503, 2.014435),
                        new Escultura("04", nom, material, 25.0, 10.0, 500.0, 2005, audio2, imatges, 41.762471, 2.017482),
                        new Escultura("05", nom, material, 25.0, 10.0, 500.0, 2005, audio2, imatges, 41.760278, 2.019059)
                );

                // Bucle que fa la inserció de les escultures.
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

    public Blob obtenirAssetAsBlob(Drawable drawable) {
        BitmapDrawable bitDw = ((BitmapDrawable) drawable);
        Bitmap bitmap = bitDw.getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] imageInByte = stream.toByteArray();

        return Blob.fromBytes(imageInByte);
    }
}