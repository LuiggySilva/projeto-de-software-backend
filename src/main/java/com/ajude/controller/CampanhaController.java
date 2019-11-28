package com.ajude.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import com.ajude.model.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ajude.service.CampanhaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@Api(value = "Controler de Campanhas")
@RestController
@RequestMapping("/api")
public class CampanhaController {
	
	@Autowired
	CampanhaService campanhaService;

	
	@ApiOperation(value = "Cadastra Campanha")
	@PostMapping("/campanha")
	public ResponseEntity<Campanha> cadastrarCampanha(@RequestBody CampanhaDTO c, @RequestHeader ("Authorization") String header){
		Campanha resposta = this.campanhaService.cadastrarCampanha(c,header);
		if(resposta != null){
			return new ResponseEntity<Campanha>(resposta, HttpStatus.OK);
		}
		return new ResponseEntity<Campanha>(HttpStatus.BAD_REQUEST);
	}
	@ApiOperation(value = "Adicionar Comentario")
	@PutMapping("/campanha/comentario/{id}")
	public ResponseEntity<Campanha> adicionarComentario(@PathVariable long id, @RequestBody Comentario comentario, @RequestHeader("Authorization") String header) {
		boolean resul = this.campanhaService.fazerComentarioCampanha(header, id, comentario);
		if(resul) {
			return new ResponseEntity<Campanha>(this.campanhaService.recuperaCampanha(id), HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Campanha>(HttpStatus.NOT_FOUND);
		}
	}
	
	@ApiOperation(value = "Adiciona Like na Campanha")
	@PutMapping("/campanha/like/{id}")
	public ResponseEntity<Campanha> Like(@PathVariable long id, @RequestHeader("Authorization") String header) {
		boolean resul = this.campanhaService.Like(header, id);
		if(resul) {
			return new ResponseEntity<Campanha>(this.campanhaService.recuperaCampanha(id), HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Campanha>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	@ApiOperation(value = "Recupera Campanha pelo Id")
	@RequestMapping("/campanha/{id}")
	public ResponseEntity<Campanha> recuperarCampanha(@PathVariable long id) {
		Campanha c = this.campanhaService.recuperaCampanha(id);
		if(c != null) {
			return new ResponseEntity<Campanha>(this.campanhaService.recuperaCampanha(id), HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Campanha>(HttpStatus.NOT_FOUND);
		}
	}
	
	@ApiOperation(value = "Lista todas as Campanha")
	@RequestMapping("/campanha/list")
	public ResponseEntity<Collection<Campanha>> recuperarCampanhas() {
		return new ResponseEntity<Collection<Campanha>>(this.campanhaService.recuperarCampanhas(), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Deleta um comentario pelo Id")
	@DeleteMapping("/campanha/comentario/{id}")
	public ResponseEntity<Campanha> deletarComentario(@PathVariable long id,@RequestHeader("Authorization") String header){
		Campanha resul = this.campanhaService.removerComentarioCampanha(header, id);
		if(resul != null) {
			return new ResponseEntity<Campanha>(HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Campanha>(HttpStatus.UNAUTHORIZED);
		}
	}

	@ApiOperation(value = "Responde um Comentario pelo Id")
	@PutMapping("/campanha/comentario/resposta/{id}")
	public ResponseEntity<Campanha> responderComentario(@PathVariable long id,@RequestHeader("Authorization") String header, @RequestBody Comentario comentario){
		Campanha resul = campanhaService.responderComentarioCampanha(id, comentario, header);
		if(resul != null) {
			return new ResponseEntity<Campanha>(HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Campanha>(HttpStatus.UNAUTHORIZED);
		}
	}
	
	@ApiOperation(value = "Recupera todos os doadores de uma Campanha pelo Id")
	@RequestMapping("/campanha/usuarios/{id}")
	public  ResponseEntity<Collection<String>> recuperaDoadoresCampanha(@PathVariable long id){
		ArrayList<String> usuarios = this.campanhaService.recuperaDoadoresCampanha(id);
		if(usuarios != null) {
			return new ResponseEntity<Collection<String>>(usuarios, HttpStatus.OK);
		}
		else{
			return new ResponseEntity<Collection<String>>(HttpStatus.NOT_FOUND);
		}
	}
	@ApiOperation(value = "Recupera Uma Campanha pelo URL")
	@RequestMapping("/campanha/url/{url}")
	public  ResponseEntity<Campanha> procurarCampanhaUrl(@PathVariable String url){
		return new ResponseEntity<Campanha>(this.campanhaService.getCampanhaByURL(url), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Recupera Campanha por um Filtro")
	@PutMapping("/campanha/search/{filtro}/{ord}")
	public  ResponseEntity<Collection<Campanha>> procurarCamapanhasFiltradas(@PathVariable("filtro") String filtro,@PathVariable("ord") String ord,@RequestBody (required = false) String substring){
		return new ResponseEntity<Collection<Campanha>>(this.campanhaService.getCampanhaPorParametros(substring,filtro,ord), HttpStatus.OK);
	}
}

