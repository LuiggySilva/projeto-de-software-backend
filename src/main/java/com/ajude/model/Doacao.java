package com.ajude.model;


import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

@Entity
public class Doacao {


    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne
    private Usuario usuario;
    @JsonIgnore
    @ManyToOne
    private Campanha campanha;
    private String data;
    @NotNull
    @Min(1)
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
