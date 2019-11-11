package com.ajude.DAO;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ajude.model.Comentario;

@Repository
public interface ComentarioDAO <T,ID extends Serializable> extends JpaRepository<Comentario ,Long>{

}
