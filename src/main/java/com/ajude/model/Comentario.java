package com.ajude.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
public class Comentario {
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	
	@ManyToOne
	@JsonIgnore
	private Comentario comentarioPai;

	@ManyToOne//(fetch = FetchType.LAZY)
	@JsonIgnore
	@JoinColumn(name = "campanha_id")
	private Campanha campanha;
	
	@ManyToOne
	private Usuario usuario;
	private String data;
	private String comentario;
	
	
	@OneToMany(mappedBy = "comentarioPai",
			cascade = CascadeType.ALL)
	private List<Comentario> respostas;
	
    public Comentario(){
        super();
    }
    
    public Comentario(Campanha campanha, Usuario usuario, String comentarioContent) {
    	super();
    	this.campanha = campanha;
    	this.usuario= usuario;
    	this.data = (new Date()).toString();
    	this.comentario = comentarioContent;
    	this.respostas = new ArrayList<Comentario>();
    }

    public Comentario(Usuario usuario, String comentarioContent,Comentario comentarioPai) {
    	super();
    	this.usuario= usuario;
    	this.data = (new Date()).toString();
    	this.comentario = comentarioContent;
    	this.comentarioPai = comentarioPai;
    	respostas = new ArrayList<Comentario>();
    }
	public Campanha getCampanha() {
		return campanha;
	}

	public void setCampanha(Campanha campanha) {
		this.campanha = campanha;
	}

	public String getUsuario() {
		return usuario.getEmail();
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
    
    public List<Comentario> getRespostas() {
		return respostas;
	}
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public Comentario getComentarioPai() {
		return comentarioPai;
	}

	public void setComentarioPai(Comentario comentarioPai) {
		this.comentarioPai = comentarioPai;
	}

	public void setRespostas(List<Comentario> respostas) {
		this.respostas = respostas;
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
