package com.ajude.DAO;

import java.io.Serializable;
import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ajude.model.Campanha;

import org.springframework.stereotype.Repository;


@Repository
public interface CampanhaDAO <T,ID extends Serializable> extends JpaRepository<Campanha ,Long> {
	
	@Query(value = "select c from Campanha c where UPPER(c.nomeCurto) like UPPER(CONCAT('%',:pcamp,'%'))")
	public Collection<Campanha> findBySubString(@Param("pcamp") String camp);
	
	/*
	@Query(value = "select c from Campanha c where UPPER(c.nomeCurto) like UPPER(CONCAT('%',:substring,'%')) and c.status = UPPER('%',:filtro,'%')")
	public Collection<Campanha> findBySubString(@Param("substring") String substring,@Param("filtro") String filtro);
	*/
}



