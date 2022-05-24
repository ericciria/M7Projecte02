package com.ericciria.m7projecte02;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Debug;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.Blob;

import java.io.ByteArrayOutputStream;
import java.io.Console;
import java.util.ArrayList;

public class ArtistaRVAdapter extends RecyclerView.Adapter <ArtistaRVAdapter.ArtistaHolder> {

    private ArrayList<Artista> artistes;

    // Constructor que inicialitza l'Adapter del RecyclerView. Li passarem l'ArrayList
    // que conté les dades dels elements.
    public ArtistaRVAdapter(ArrayList<Artista> Artista) {
        this.artistes = Artista;
    }

    // Classe "holder" que proporciona una referència a les vistes de cada element
    // de la llista.
    public static class ArtistaHolder extends RecyclerView.ViewHolder {
        public View element;
        public ArtistaHolder(View v) {
            super(v);
            element = v;
        }
    }

    // Crea les noves vistes dels elements del RecyclerView. Aquest mètode
    // és cridat pel LayoutManager.
    public ArtistaRVAdapter.ArtistaHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Crea una nova vista d'un element, a partir de la vista en format
        // XML que hem creat prèviament amb el disseny.
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_artista, parent, false);

        return new ArtistaRVAdapter.ArtistaHolder(v);
    }

    // Mètode que dóna contingut a les vistes dels elements del RecyclerView. Aquest
    // mètode el crida el LayoutManager.
    @Override
    public void onBindViewHolder(ArtistaRVAdapter.ArtistaHolder holder, @SuppressLint("RecyclerView") int position) {

        ImageView iv = (ImageView) holder.element.findViewById(R.id.ivFoto);
        if(obtenirBlobAsBitmap(artistes.get(position).getFoto())!=null){
            iv.setImageBitmap(obtenirBlobAsBitmap(artistes.get(position).getFoto()));
        }


        // Enllacem el TextView del nom de l'element amb l'atribut nom
        TextView tvNom = (TextView)holder.element.findViewById(R.id.tvNom);
        tvNom.setText(artistes.get(position).getNom() + " " + artistes.get(position).getCognoms());
        TextView tvEscultures = (TextView)holder.element.findViewById(R.id.tvEscultures);
        tvNom.setText(artistes.get(position).getNom());
        tvEscultures.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), A04_2_Escultures.class);
                intent.putExtra("id", artistes.get(position).getIdArtista());
                v.getContext().startActivity(intent);
            }
        });

    }

    // Mètode que retorna el nombre d'elements del DataSet que mostrem a la llista.
    // Aquest mètode el crida el LayoutManager.
    public int getItemCount() {
        return artistes.size();
    }

    public Bitmap obtenirBlobAsBitmap(Blob blob) {
        if(blob!=null){
            byte [] bytes = blob.toBytes();
            return BitmapFactory.decodeByteArray(bytes, 0 ,bytes.length);
        }
        else{
            return null;
        }
    }
}
