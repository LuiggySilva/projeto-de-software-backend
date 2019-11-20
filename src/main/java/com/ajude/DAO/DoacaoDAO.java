package com.ajude.DAO;

import com.ajude.model.Doacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;


@Repository
public interface DoacaoDAO <T,Id extends Serializable> extends JpaRepository<Doacao,Long> {

}
