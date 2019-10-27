package com.ajude.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
@Table(name = "like_campanha")
public class Like {
	
	@Id @GeneratedValue
	private long id;
	
	@ManyToOne
	@JoinColumn(name = "idCampanha")
	private Campanha campanha;
	
	@ManyToOne
	@JoinColumn(name = "email")
    private Usuario user;

    public Like(Usuario user,Campanha campanha){
        this.user = user;
        this.campanha = campanha;
    }
    
    public Like() {
    	super();
    }

}
