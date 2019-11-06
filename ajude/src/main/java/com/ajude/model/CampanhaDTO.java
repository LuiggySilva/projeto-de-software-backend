package com.ajude.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;


public class CampanhaDTO{


        private String nomeCurto;
        private String descricao;
        private String deadLine;
        private StatusCampanha status;
        private Double meta;

        public CampanhaDTO(){
            super();
        }

        public  CampanhaDTO(String nomeCurto,String descricao, String deadLine,Double meta){
            this.nomeCurto = nomeCurto;
            this.descricao = descricao;
            this.meta = meta;
            this.status = StatusCampanha.ATIVA;
        }
        public String getNomeCurto() {
            return nomeCurto;
        }

        public void setNomeCurto(String nomeCurto) {
            this.nomeCurto = nomeCurto;
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

        public Campanha makeCampanha(Usuario user){
            return  new Campanha(nomeCurto, descricao, deadLine, meta , user);
        }




}

