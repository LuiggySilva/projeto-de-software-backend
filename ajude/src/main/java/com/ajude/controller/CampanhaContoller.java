package com.ajude.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ajude.model.Campanha;
import com.ajude.model.Usuario;
import com.ajude.service.CampanhaService;

@RestController
public class CampanhaContoller {
	
	@Autowired
	CampanhaService campanhaService;
	Usuario u = new Usuario("Luiggy", "Silva", "luiggy.silva@gmail.com", "666", "12345");
	
	@PostMapping("/campanha")
	public ResponseEntity<Campanha> cadastrarCampanha(@RequestBody Campanha c) {
		c.setDono(u);
		return new ResponseEntity<Campanha>(this.campanhaService.cadastrarCampanha(c), HttpStatus.OK);
	}
	
	@PutMapping("/campanha/{id}")
	public ResponseEntity<Campanha> cadastrarCampanha(@PathVariable long id, @RequestBody String c) {
		boolean resul = this.campanhaService.fazerComentarioCampanha(u, id, c);
		if(resul) {
			return new ResponseEntity<Campanha>(this.campanhaService.recuperaCampanha(id), HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Campanha>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping("/campanha/{id}")
	public ResponseEntity<Campanha> recuperarCampanha(@PathVariable int id) {
		return new ResponseEntity<Campanha>(this.campanhaService.recuperaCampanha(id), HttpStatus.OK);
	}
	
	@RequestMapping("/campanha/list")
	public ResponseEntity<Collection<Campanha>> recuperarCamapnhas() {
		return new ResponseEntity<Collection<Campanha>>(this.campanhaService.recuperarCampanhas(), HttpStatus.OK);
	}
}
