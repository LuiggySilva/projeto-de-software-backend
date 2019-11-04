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
    
    public Campanha getCampanha() {
		return campanha;
	}
    
    public Usuario getUser() {
		return user;
	}
    
    public void setCampanha(Campanha campanha) {
		this.campanha = campanha;
	}
    
    public void setUser(Usuario user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((campanha == null) ? 0 : campanha.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Like other = (Like) obj;
		if (campanha == null) {
			if (other.campanha != null)
				return false;
		} else if (!campanha.equals(other.campanha))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}
    
}
