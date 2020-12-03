package com.rifa.mrrifa.model;

import com.google.firebase.database.DatabaseReference;
import com.rifa.mrrifa.config.ConfiguracaoFirebase;

import java.io.Serializable;

public class Humano implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String nome;
    private String nomeChave;
    private String wpp;
    private String qtde;
    private String venderdor;

    public Humano() {

    }

    public Humano(String nome, String wpp, String qtde, String venderdor) {
        this.nome = nome;
        this.wpp = wpp;
        this.qtde = qtde;
        this.venderdor = venderdor;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeChave() {
        return nomeChave;
    }

    public void setNomeChave(String nomeChave) {
        this.nomeChave = nomeChave;
    }

    public String getWpp() {
        return wpp;
    }

    public void setWpp(String wpp) {
        this.wpp = wpp;
    }

    public String getQtde() {
        return qtde;
    }

    public void setQtde(String qtde) {
        this.qtde = qtde;
    }

    public String getVenderdor() {
        return venderdor;
    }

    public void setVenderdor(String venderdor) {
        this.venderdor = venderdor;
    }

    public void salvar() {
        setNomeChave(getNome().toUpperCase());
        DatabaseReference usuarioRef = ConfiguracaoFirebase.getFirebaseDatabase().child("utteam").child(getId());
        usuarioRef.setValue(this);
    }

    public void remover() {
        DatabaseReference anuncioRef = ConfiguracaoFirebase.getFirebaseDatabase().child("utteam").child(getId());
        anuncioRef.removeValue();
    }

}
