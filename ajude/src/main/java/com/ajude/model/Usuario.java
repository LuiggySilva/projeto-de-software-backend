package com.ajude.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Usuario {

	private String nome;
	private String sobrenome;
	@Id
	private String email;
	private String cartaoCredito;
	@SuppressWarnings("unused")
	private String senha;
	
	public Usuario() {
	}
	
	public Usuario(String nome, String sobrenome, String email, String cartaoCredito, String senha) {
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.email = email;
		this.cartaoCredito = cartaoCredito;
		this.senha = senha;
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
}	
