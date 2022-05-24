package com.ericciria.m7projecte02;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class A04_2_Escultures extends AppCompatActivity {

    String id;
    FirebaseFirestore db;
    TextView tvId, tvSize, tvYear, tvPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a042_escultures);

        Intent intent=getIntent();
        id = intent.getStringExtra("id");

        db = FirebaseFirestore.getInstance();

        tvId = findViewById(R.id.tvId);
        tvSize = findViewById(R.id.tvAltura);
        tvYear = findViewById(R.id.tvAny);
        tvPosition = findViewById(R.id.tvLatitud);
    }

    @Override
    protected void onResume() {
        super.onResume();

        DocumentReference docRef = db.collection("escultures").document(id);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document != null) {
                        tvId.setText("Id: " + document.get("idEscultura").toString());
                        tvSize.setText("Altura: " + document.get("altura").toString() + " - "
                                + "Amplada: " + document.get("amplada").toString() + " - "
                                + "Pes: " + document.get("pes").toString());
                        tvYear.setText("Any: " + document.get("any").toString());
                        tvPosition.setText("Latitud: " + document.get("latitud").toString()
                                + "Longitud: " + document.get("longitud").toString());
                    } else {

                    }
                } else {

                }
            }
        });
    }
}