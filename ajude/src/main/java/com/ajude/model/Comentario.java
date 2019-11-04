package com.ajude.model;

import java.util.ArrayList;
import java.util.Date;
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
	private String data;
	private String comentario;
	private ArrayList<Comentario> respostas;
	
    public Comentario(){
        super();
    }
    
    public Comentario(Campanha campanha, Usuario usuario, String comentario) {
    	this.campanha = campanha;
    	this.usuario= usuario;
    	this.data = (new Date()).toString();
    	this.comentario = comentario;
    	this.respostas = new ArrayList<Comentario>();
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
    
    public String getData() {
		return data;
	}
    
    public void setData(String data) {
		this.data = data;
	}
    
    public String getComentario() {
		return comentario;
	}
    
    public void setComentario(String comentario) {
		this.comentario = comentario;
	}
    
    public ArrayList<Comentario> getRespostas() {
		return respostas;
	}
    
    public void setRespostas(Comentario resposta) {
		this.respostas.add(resposta);
	}
    
	public void removerResposta(long idRepost) {
		Comentario x = null;
		for (Comentario comentario : respostas) {
			if (comentario.getId() == idRepost) {
				x = comentario;
				break;
			}
		}
		this.respostas.remove(x);
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((campanha == null) ? 0 : campanha.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
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
		Comentario other = (Comentario) obj;
		if (campanha == null) {
			if (other.campanha != null)
				return false;
		} else if (!campanha.equals(other.campanha))
			return false;
		if (id != other.id)
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}
    
}
