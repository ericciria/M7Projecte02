package com.ericciria.m7projecte02;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

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
    public void onBindViewHolder(ArtistaRVAdapter.ArtistaHolder holder, int position) {

        // Enllacem el TextView del nom de l'element amb l'atribut nom
        TextView tvNom = (TextView)holder.element.findViewById(R.id.tvNom);
        tvNom.setText(artistes.get(position).getNom());

        /*
        // Enllacem el TextView de l'origen de l'element amb els atributs
        TextView tvPunts = (TextView)holder.element.findViewById(R.id.tvOrigen);
        tvPunts.setText(String.format("%s (%s)",
                escultures.get(position).getAltura(),
                escultures.get(position).getAmplada()));

        // Enllacem el TextView de la capacitat amb l'atribut capacitat.
        TextView tvCapacitatCL = (TextView)holder.element.findViewById(R.id.tvCapacitatCL);
        tvCapacitatCL.setText(String.format("%d cl", escultures.get(position).getAny()));

        // Enllacem el TextView de la graduació amb l'atribut graduació.
        TextView tvGraduacio = (TextView)holder.element.findViewById(R.id.tvGraduacio);
        tvGraduacio.setText(String.format("%.1fº", escultures.get(position).getLatitud()));*/

        // Enllacem el ImageView de la foto amb l'atribut foto.
        /*byte[] foto = escultures.get(position).getFoto();
        ImageView ivFoto = (ImageView) holder.element.findViewById(R.id.ivFotoRatafia);
        Bitmap bmp = BitmapFactory.decodeByteArray(foto, 0, foto.length);
        ivFoto.setImageBitmap(bmp);*/
    }

    // Mètode que retorna el nombre d'elements del DataSet que mostrem a la llista.
    // Aquest mètode el crida el LayoutManager.
    public int getItemCount() {
        return artistes.size();
    }
}
