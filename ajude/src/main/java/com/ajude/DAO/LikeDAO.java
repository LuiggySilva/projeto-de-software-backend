package com.ajude.DAO;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ajude.model.Like;

@Repository
public interface LikeDAO<T,ID extends Serializable> extends  JpaRepository<Like, Long>{

}
