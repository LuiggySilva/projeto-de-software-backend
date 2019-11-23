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

@RestController
public class CampanhaController {
	
	@Autowired
	CampanhaService campanhaService;

	@PostMapping("/campanha")
	public ResponseEntity<Campanha> cadastrarCampanha(@RequestBody CampanhaDTO c, @RequestHeader ("Authorization") String header){
		Campanha resposta = this.campanhaService.cadastrarCampanha(c,header);
		if(resposta != null){
			return new ResponseEntity<Campanha>(resposta, HttpStatus.OK);
		}
		return new ResponseEntity<Campanha>(HttpStatus.BAD_REQUEST);
	}
	
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
	
	@RequestMapping("/campanha/list")
	public ResponseEntity<Collection<Campanha>> recuperarCampanhas() {
		return new ResponseEntity<Collection<Campanha>>(this.campanhaService.recuperarCampanhas(), HttpStatus.OK);
	}
	
	@RequestMapping("/campanha/like/list")
	public ResponseEntity<Collection<Like>> testando() {
		return new ResponseEntity<Collection<Like>>(this.campanhaService.like(), HttpStatus.OK);
	}
	
	@DeleteMapping("/campanha/comentario/{id}")
	public ResponseEntity<Comentario> deletarComentario(@PathVariable long id,@RequestHeader("Authorization") String header){
		boolean resul = this.campanhaService.removerComentarioCampanha(header, id);
		if(resul) {
			return new ResponseEntity<Comentario>(HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Comentario>(HttpStatus.UNAUTHORIZED);
		}
		
	}
	
	@GetMapping("/comentario/list")
	public ResponseEntity<Collection<Comentario>> listarComentariosTest(){
		return new ResponseEntity<Collection<Comentario>> (this.campanhaService.comentarios(),HttpStatus.OK);
	}
	
	@PutMapping("/campanha/comentario/resposta/{id}")
	public ResponseEntity<Comentario> responderComentario(@PathVariable long id,@RequestHeader("Authorization") String header, @RequestBody Comentario comentario){
		Comentario resul = campanhaService.responderComentarioCampanha(id, comentario, header);
		if(resul != null) {
			return new ResponseEntity<Comentario>(resul, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Comentario>(HttpStatus.NOT_FOUND);
		}
	}
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
	
	@RequestMapping("/campanha/search")
	public  ResponseEntity<Collection<Campanha>> procurarCampanhaSubString(@RequestBody String searching){
		return new ResponseEntity<Collection<Campanha>>(this.campanhaService.findBySubString(searching), HttpStatus.OK);
	}
	
	@RequestMapping("/campanha/url/{url}")
	public  ResponseEntity<Campanha> procurarCampanhaUrl(@PathVariable String url){
		return new ResponseEntity<Campanha>(this.campanhaService.getCampanhaByURL(url), HttpStatus.OK);
	}
	
	@RequestMapping("/campanha/ordenedBy/{ord}")
	public  ResponseEntity<Collection<Campanha>> recuperarCampanhasOrdenadas(@PathVariable String ord){
		return new ResponseEntity<Collection<Campanha>>(this.campanhaService.getCampanhasOrdenadas(ord), HttpStatus.OK);
	}
	

}
