package com.ajude.util;

import java.util.Comparator;

import com.ajude.model.Campanha;

public class OrdenarCampanhaDeadLine implements Comparator<Campanha> {

	@Override
	public int compare(Campanha o1, Campanha o2) {
		boolean resul = o2.getDeadLine().isBefore(o1.getDeadLine());	
		if (resul) {
			return 1;
		} else {
			return 0;
		}
	}
}
