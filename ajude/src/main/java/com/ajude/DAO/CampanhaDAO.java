package com.ajude.DAO;

import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ajude.model.Campanha;
import org.springframework.stereotype.Repository;


@Repository
public interface CampanhaDAO <T,ID extends Serializable> extends JpaRepository<Campanha ,Long> {

}



