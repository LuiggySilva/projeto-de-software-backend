package com.ajude.model;

import sun.util.calendar.BaseCalendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.text.Normalizer;
import java.util.regex.Pattern;

@Entity
public class Campanha {

    @Id @GeneratedValue
    private long idCampanha;
    private String nomeCurto;
    //Gerado pelo frontend
    private String url;
    private String descricao;
    private String deadLine;
    //Pode ser um enum
    private StatusCampanha status;
    private Double meta;
    // atributo doacoes faltando aqui
    @ManyToOne
    private Usuario dono;
    @OneToMany
    private List<Comentario> comentarios;
    @OneToMany
    private List<Like> likes;

    public Campanha(){
        super();
    }

    public  Campanha(String nomeCurto,String descricao, String deadLine,Double meta,Usuario dono){
        this.nomeCurto = nomeCurto;
        this.descricao = descricao;
        this.meta = meta;
        this.dono = dono;
        this.url = makeUrl(this.nomeCurto);
        this.status = StatusCampanha.ATIVA;
        this.comentarios = new LinkedList<Comentario>();
        this.likes = new LinkedList<Like>();
    }


    private static String makeUrl(String str) {
        String nfdNormalizedString = Normalizer.normalize(str, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(nfdNormalizedString).replaceAll("").replace(" ","-").replaceAll("[!#$%&'()*+,.:;?@[\\\\]_`{|}~]","").toLowerCase();
    }

	public long getId() {
		return idCampanha;
	}



	public String getNomeCurto() {
		return nomeCurto;
	}

	public void setNomeCurto(String nomeCurto) {
		this.nomeCurto = nomeCurto;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDeadLine() {
		return deadLine;
	}

	public void setDeadLine(String deadLine) {
		this.deadLine = deadLine;
	}

	public StatusCampanha getStatus() {
		return status;
	}

	public void setStatus(StatusCampanha status) {
		this.status = status;
	}

	public Double getMeta() {
		return meta;
	}

	public void setMeta(Double meta) {
		this.meta = meta;
	}

	public Usuario getDono() {
		return dono;
	}

	public void setDono(Usuario dono) {
		this.dono = dono;
	}

	public List<Like> getLikes() {
		return likes;
	}

	public void setLikes(List<Like> likes) {
		this.likes = likes;
	}

	public List<Comentario> getComentarios() {
		return comentarios;
	}
    
    
}
