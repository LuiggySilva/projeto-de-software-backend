package com.ajude.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Usuario {

	private String nome;
	private String sobrenome;
	@Id
	private String email;
	private String cartaoCredito;
	private String senha;
	@OneToMany
	private List<Doacao> listaDoacoes;

	public Usuario() {
			super();
	}
	
	public Usuario(String nome, String sobrenome, String email, String cartaoCredito, String senha) {
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.email = email;
		this.cartaoCredito = cartaoCredito;
		this.senha = senha;
		this.listaDoacoes = new ArrayList<Doacao>();
	}

	public List<Doacao> getListaDoacoes() {
		return listaDoacoes;
	}

	public void setListaDoacoes(List<Doacao> listaDoacoes) {
		this.listaDoacoes = listaDoacoes;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCartaoCredito() {
		return cartaoCredito;
	}

	public void setCartaoCredito(String cartaoCredito) {
		this.cartaoCredito = cartaoCredito;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
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
		Usuario other = (Usuario) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		return true;
	}
}	


