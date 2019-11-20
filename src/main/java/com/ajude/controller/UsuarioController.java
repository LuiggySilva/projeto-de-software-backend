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
		return new ResponseEntity<Usuario>(this.usuarioService.cadastrarUsuario(u), HttpStatus.OK);
	}
	
	@RequestMapping("/usuario")
	public ResponseEntity<Usuario> recuperarUsuario(@RequestBody String email) {
		return new ResponseEntity<Usuario>(this.usuarioService.recuperarUsuario(email), HttpStatus.OK);
	}
	
	@RequestMapping("/usuario/list")
	public ResponseEntity<Collection<Usuario>> recuperarUsuarios() {
		return new ResponseEntity<Collection<Usuario>>(this.usuarioService.recuperarUsuarios(), HttpStatus.OK);
	}

	@RequestMapping("/usuario/campanha/list")
	public ResponseEntity<Collection<Campanha>> recuperaCampanhasDoadas(@RequestBody String email){
		return new ResponseEntity<Collection<Campanha>>(this.usuarioService.recuperaCampanhasDoadasUsuario(email), HttpStatus.OK);
	}

	@PutMapping("/usuario/campanha/doacao/{id}")
	public  ResponseEntity<Doacao> doaCampanha(@RequestHeader("Authorization")String header ,@PathVariable long id,@RequestBody Doacao doacao){
		Doacao novaDoacao = this.usuarioService.fazerDoacaoCampanha(header ,id, doacao);
		if(novaDoacao != null){
			return new ResponseEntity<Doacao>(novaDoacao,HttpStatus.OK);
		}else{
			return new ResponseEntity<Doacao>(HttpStatus.NOT_FOUND);
		}
	}
	
}
