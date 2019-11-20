package com.ajude.model;


import javax.persistence.*;
import java.util.Date;

@Entity
public class Doacao {


    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne
    private Usuario usuario;
    @ManyToOne
    private Campanha campanha;
    private String data;
    private Double doacao;

    public Doacao(){
        super();
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Double getDoacao() {
        return doacao;
    }

    public void setDoacao(Double doacao) {
        this.doacao = doacao;
    }

    public Doacao(Campanha campanha, Usuario usuario, Double doacao ) {
        this.campanha = campanha;
        this.usuario = usuario;
        data = (new Date()).toString();
        this.doacao = doacao;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario.getEmail();
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Campanha getCampanha() {
        return campanha;
    }

    public void setCampanha(Campanha campanha) {
        this.campanha = campanha;
    }
}
