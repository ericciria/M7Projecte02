package com.ericciria.m7projecte02;

import java.util.Map;

public class Artista {
    private String idArtista;
    private String nom;
    private String cognoms;
    private Integer anyNaixement;
    private Integer anyDefuncio;
    private Map<String, String> biografia;
    private Map<String, String> correntArtistic;
    private Map<String, Byte[]> audio;
    private Byte[] foto;

    public Artista() {
    }

    public Artista(String idArtista, String nom, String cognoms, Integer anyNaixement, Integer anyDefuncio,
                   Map<String, String> biografia, Map<String, String> correntArtistic,
                   Map<String, Byte[]> audio, Byte[] foto) {
        this.idArtista = idArtista;
        this.nom = nom;
        this.cognoms = cognoms;
        this.anyNaixement = anyNaixement;
        this.anyDefuncio = anyDefuncio;
        this.biografia = biografia;
        this.correntArtistic = correntArtistic;
        this.audio = audio;
        this.foto = foto;
    }

    public String getIdArtista() {
        return idArtista;
    }

    public void setIdArtista(String idArtista) {
        this.idArtista = idArtista;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCognoms() {
        return cognoms;
    }

    public void setCognoms(String cognoms) {
        this.cognoms = cognoms;
    }

    public Integer getAnyNaixement() {
        return anyNaixement;
    }

    public void setAnyNaixement(Integer anyNaixement) {
        this.anyNaixement = anyNaixement;
    }

    public Integer getAnyDefuncio() {
        return anyDefuncio;
    }

    public void setAnyDefuncio(Integer anyDefuncio) {
        this.anyDefuncio = anyDefuncio;
    }

    public Map<String, String> getBiografia() {
        return biografia;
    }

    public void setBiografia(Map<String, String> biografia) {
        this.biografia = biografia;
    }

    public Map<String, String> getCorrentArtistic() {
        return correntArtistic;
    }

    public void setCorrentArtistic(Map<String, String> correntArtistic) {
        this.correntArtistic = correntArtistic;
    }

    public Map<String, Byte[]> getAudio() {
        return audio;
    }

    public void setAudio(Map<String, Byte[]> audio) {
        this.audio = audio;
    }

    public Byte[] getFoto() {
        return foto;
    }

    public void setFoto(Byte[] foto) {
        this.foto = foto;
    }
}
