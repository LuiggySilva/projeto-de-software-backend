package com.ajude.model;

public enum StatusCampanha {

	ATIVA("ATIVA"),ENCERRADA("ENCERRADA"),VENCIDA("VENCIDA"),CONCLUIDA("CONCLUIDA");
	
	private String status;

	StatusCampanha(String status) {
		this.status = status;
	}
	
	public String getStatus() {
		return this.status.strip();
	}
}
