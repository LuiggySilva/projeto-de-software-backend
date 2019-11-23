package com.ajude.util;

import java.util.Comparator;

import com.ajude.model.Campanha;

public class OrdenarCampanhaMeta implements Comparator<Campanha> {

	@Override
	public int compare(Campanha o1, Campanha o2) {
		return (int) (o2.getPorcentagemConcluidaMeta() - o1.getPorcentagemConcluidaMeta());
	}
}
