package com.ajude.model;

import sun.util.calendar.BaseCalendar;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
import java.util.List;

@Entity
public class Campanha {

    @Id
    private int id;
    private String nomeCurto;
    //Gerado pelo frontend
    private String url;
    private String descricao;
    private String deadLine;
    //Pode ser um enum
    private String status;
    private Double meta;
    // atributo doacoes faltando aqui
    private Usuario dono;
    private List<Comentario> comentarios;
    private List<Like> likes;
}
