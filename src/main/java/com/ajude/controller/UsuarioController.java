package com.ajude.controller;

import java.util.Collection;

import com.ajude.model.Campanha;
import com.ajude.model.Doacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ajude.model.Usuario;
import com.ajude.service.UsuarioService;

@RestController
public class UsuarioController {
	
	@Autowired
	UsuarioService usuarioService;
	
	@PostMapping("/usuario")
	public ResponseEntity<Usuario> cadastrarUsuario(@RequestBody Usuario u) {
		Usuario user = this.usuarioService.cadastrarUsuario(u);
		if(user != null) {
			return new ResponseEntity<Usuario>(user, HttpStatus.OK);
		}
		return new ResponseEntity<Usuario>(HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping("/usuario/email/{email}")
	public ResponseEntity<Usuario> recuperarUsuario(@PathVariable String email) {
		Usuario usuario =  this.usuarioService.recuperarUsuario(email);
		if(usuario != null) {
			return new ResponseEntity<Usuario>(this.usuarioService.recuperarUsuario(email), HttpStatus.OK);
		}
		return new ResponseEntity<Usuario>( HttpStatus.NOT_FOUND);
	}
	
	@PutMapping("/usuario/token")
	public ResponseEntity<Usuario> recuperarUsuarioToken(@RequestBody String token) {
		Usuario usuario =  this.usuarioService.recuperaUsuarioToken(token);
		if(usuario != null) {
			return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
		}
		return new ResponseEntity<Usuario>( HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping("/usuario/list")
	public ResponseEntity<Collection<Usuario>> recuperarUsuarios() {
		return new ResponseEntity<Collection<Usuario>>(this.usuarioService.recuperarUsuarios(), HttpStatus.OK);
	}

	@RequestMapping("/usuario/campanha/list/{email}")
	public ResponseEntity<Collection<Campanha>> recuperaCampanhasDoadas(@PathVariable String email){
		return new ResponseEntity<Collection<Campanha>>(this.usuarioService.recuperaCampanhasDoadasUsuario(email), HttpStatus.OK);
	}

	@PutMapping("/usuario/campanha/doacao/{id}")
	public  ResponseEntity<Campanha> doaCampanha(@RequestHeader("Authorization")String header ,@PathVariable long id,@RequestBody Doacao doacao){
		Campanha c = this.usuarioService.fazerDoacaoCampanha(header ,id, doacao);
		if(c != null){
			return new ResponseEntity<Campanha>(c,HttpStatus.OK);
		}else{
			return new ResponseEntity<Campanha>(HttpStatus.NOT_FOUND);
		}
	}
	
}
