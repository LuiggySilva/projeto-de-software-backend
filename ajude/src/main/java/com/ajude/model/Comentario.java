package com.ajude.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
public class Comentario {
	
	
	
	@Id @GeneratedValue
	private long id;
	
	@ManyToOne
	@JoinColumn(name =  "idCampanha")
	@JsonIgnore
	private Campanha campanha;
	
	@ManyToOne
	@JoinColumn(name = "email")
	@JsonIgnore
	private Usuario usuario;
	
	
    public Comentario(){
        super();
    }
    
    public Comentario(Campanha campanha,Usuario usuario) {
    	this.campanha = campanha;
    	this.usuario= usuario;
    }

	public Campanha getCampanha() {
		return campanha;
	}

	public void setCampanha(Campanha campanha) {
		this.campanha = campanha;
	}

	public Usuario getUsuario() {
		return usuario;
	}
    
    


}
