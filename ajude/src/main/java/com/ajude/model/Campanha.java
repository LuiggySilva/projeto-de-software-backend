package com.ajude.model;

import sun.util.calendar.BaseCalendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;
import java.util.List;
import java.text.Normalizer;
import java.util.regex.Pattern;

@Entity
public class Campanha {

    @Id @GeneratedValue
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

    public Campanha(){
        super();
    }

    public  Campanha(String nomeCurto,String descricao, String deadLine,Double meta,Usuario dono){
        this.nomeCurto = nomeCurto;
        this.descricao = descricao;
        this.meta = meta;
        this.dono = dono;
        this.url = makeUrl(this.nomeCurto);
    }


    private static String makeUrl(String str) {
        String nfdNormalizedString = Normalizer.normalize(str, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(nfdNormalizedString).replaceAll("").replace(" ","-").replaceAll("[!#$%&'()*+,.:;?@[\\\\]_`{|}~]","").toLowerCase();
    }
}
