package com.ajude.model;


import java.text.Normalizer;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatTypes;

@Entity
public class Campanha {

    @Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
    private String nomeCurto;
    private String url;
    private String descricao;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private LocalDate deadLine;
    private String status;
    @NotNull
    @Min(1)
    private Double meta;
   

	private Double doacoes;
	private int likesCount;
	private int comentCount ;
	private String data;
	@ManyToOne
    private Usuario dono;
	@OneToMany(mappedBy = "campanha",
			cascade = CascadeType.ALL)
	private List<Comentario> comentarios;

	@OneToMany(mappedBy = "campanha",
			cascade = CascadeType.ALL)
	private List<Doacao> listaDoacoes;


    public Campanha(){
    	super();
    }
    
    public  Campanha(String nomeCurto,String descricao, String deadLine,Double meta,Usuario dono){
    	super();
    	this.nomeCurto = nomeCurto;
        this.descricao = descricao;
        this.meta = meta;
        this.dono = dono;
        this.deadLine = LocalDate.parse(deadLine);
        this.url = makeUrl(this.nomeCurto);
        this.status = StatusCampanha.ATIVA.getStatus();
        this.likesCount = 0;
        this.comentCount = 0;
        this.doacoes = 0.0;
        this.data = (new Date()).toString();
        comentarios = new ArrayList<Comentario>();
        listaDoacoes = new ArrayList<Doacao>();
    }

    private static String makeUrl(String str) {
        String nfdNormalizedString = Normalizer.normalize(str, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(nfdNormalizedString).replaceAll("").replace(" ","-").replaceAll("[!#$%&'()*+,.:;?@[\\\\]_`{|}~]","").toLowerCase();
    }

	public long getId() {
		return this.id;
	}

	public void setId(long id){
    	this.id = id;
	}
	public String getNomeCurto() {
		return nomeCurto;
	}

	public void setNomeCurto(String nomeCurto) {
		this.nomeCurto = nomeCurto;
	}
	
	public Double getPorcentagemConcluidaMeta() {
		return ((this.doacoes * 100) / this.meta);
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void addDoacao(Double novaDoacao){
		this.doacoes += novaDoacao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDate getDeadLine() {
		return deadLine;
	}

	public void setDeadLine(LocalDate deadLine) {
		this.deadLine = deadLine;
	}

	public String getStatus() {
		if(this.deadLine.isBefore(LocalDate.now())) {
			if(this.doacoes >= this.meta) {
				this.status = StatusCampanha.CONCLUIDA.getStatus();
			}
			else {
				this.status = StatusCampanha.VENCIDA.getStatus();
			}
		}
		else {
			if(this.doacoes >= this.meta) {
				this.status = StatusCampanha.CONCLUIDA.getStatus();
			}
			else {
				this.status = StatusCampanha.ATIVA.getStatus();
			}
		}
		return status;
	}

	public void setStatus(StatusCampanha status) {
		this.status = status.getStatus();
	}

	public Double getMeta() {
		return meta;
	}

	public void setMeta(Double meta) {
		this.meta = meta;
	}

	public UsuarioDTO getDono() {
		return new UsuarioDTO(this.dono.getNome(), this.dono.getSobrenome(), this.dono.getEmail());
	}

	public void setDono(Usuario dono) {
		this.dono = dono;
	}

	public int getLikesCount() {
		return likesCount;
	}
	
	public void setLikesCount(int likesCount) {
		this.likesCount = likesCount;
	}

	public void addLike() {
		this.likesCount++;
	}
	
	public void subLike() {
		if(this.likesCount > 0) {
			this.likesCount--;
		}
	}
	
	public int getComentCount() {
		return comentCount;
	}

	public void setComentCount(int comentCount) {
		this.comentCount = comentCount;
	}
	
	public Double getDoacoes() {
		return doacoes;
	}
	
	public void setDoacoes(Double doacoes) {
		this.doacoes += doacoes;
	}
	
	public String porcentagemMeta() {
		return Double.toString( (this.getDoacoes() / this.getMeta()) * 100.0 ) + "%";
	}
	
	public List<Comentario> getComentarios() {
		return comentarios;
	}
	
	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

	public void addComentariosCount() {
		this.comentCount ++;
	}
	
	public void subComentariosCount() {
		this.comentCount --;
	}
	
	public List<Doacao> getListaDoacoes() {
		return listaDoacoes;
	}

	public void setListaDoacoes(List<Doacao> listaDoacoes) {
		this.listaDoacoes = listaDoacoes;
	}
	
	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((nomeCurto == null) ? 0 : nomeCurto.hashCode());
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
		Campanha other = (Campanha) obj;
		if (id != other.id)
			return false;
		if (nomeCurto == null) {
			if (other.nomeCurto != null)
				return false;
		} else if (!nomeCurto.equals(other.nomeCurto))
			return false;
		return true;
	} 
	
	@Override
	public String toString() {
		return "DATA " + this.deadLine +  " Nome Curto: " + this.nomeCurto + " \n" ;
		
	}
    
}
