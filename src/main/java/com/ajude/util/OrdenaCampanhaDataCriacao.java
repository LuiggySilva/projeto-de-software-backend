package com.ajude.util;

import java.util.Comparator;

import com.ajude.model.Campanha;

public class OrdenaCampanhaDataCriacao implements Comparator<Campanha>{

	@Override
	public int compare(Campanha o1, Campanha o2) {
		return (int) (o2.getId() - o1.getId()) ;
	}

}
