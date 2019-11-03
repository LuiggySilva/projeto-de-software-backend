package com.ajude.DAO;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ajude.model.Comentario;

public interface ComentarioDAO <T,ID extends Serializable> extends JpaRepository<Comentario ,Long>{

}
