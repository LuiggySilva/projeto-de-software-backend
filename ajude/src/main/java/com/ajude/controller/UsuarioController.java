package com.ajude.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
}
