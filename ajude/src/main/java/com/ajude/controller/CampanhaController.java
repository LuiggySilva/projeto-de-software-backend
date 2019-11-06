package com.ajude.controller;

import java.util.Collection;

import com.ajude.model.CampanhaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ajude.model.Campanha;
import com.ajude.model.Usuario;
import com.ajude.service.CampanhaService;

@RestController
public class CampanhaController {
	
	@Autowired
	CampanhaService campanhaService;

	@PostMapping("/campanha")
	public ResponseEntity<Campanha> cadastrarCampanha(@RequestBody CampanhaDTO c, @RequestHeader ("Authorization") String header){
		System.out.println(c.getNomeCurto());
		Campanha resposta = this.campanhaService.cadastrarCampanha(c,header);
		if(resposta != null){
			return new ResponseEntity<Campanha>(resposta, HttpStatus.OK);
		}
		return new ResponseEntity<Campanha>(HttpStatus.BAD_REQUEST);
	}
	
	@PutMapping("/campanha/{id}")
	public ResponseEntity<Campanha> adiconarComentario(@PathVariable long id, @RequestBody String comentario, @RequestHeader("Authorization") String header) {
		boolean resul = this.campanhaService.fazerComentarioCampanha(header, id, comentario);
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
