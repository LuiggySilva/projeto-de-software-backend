package com.ajude.model;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Doacao {


    @Id
    private long id;
    @ManyToOne
    private Usuario usuario;
    @ManyToOne
    private Campanha campanha;

    public Doacao(){
        super();
    }

    public Doacao(Campanha campanha,Usuario usuario) {
        this.campanha = campanha;
        this.usuario = usuario;
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
