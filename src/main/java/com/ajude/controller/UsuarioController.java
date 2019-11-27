package com.ajude.controller;

import java.util.Collection;

import com.ajude.model.Campanha;
import com.ajude.model.Doacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ajude.model.Usuario;
import com.ajude.model.UsuarioDTO;
import com.ajude.service.UsuarioService;

@RestController
public class UsuarioController {
	
	@Autowired
	UsuarioService usuarioService;
	
	@PostMapping("/usuario")
	public ResponseEntity<Usuario> cadastrarUsuario(@RequestBody Usuario u) {
		return new ResponseEntity<Usuario>(this.usuarioService.cadastrarUsuario(u), HttpStatus.OK);
	}
	
	@RequestMapping("/usuario/email/{email}")
	public ResponseEntity<UsuarioDTO> recuperarUsuario(@PathVariable String email) {
		UsuarioDTO usuario =  this.usuarioService.recuperarUsuarioPublico(email);
		if(usuario != null) {
			return new ResponseEntity<UsuarioDTO>(usuario, HttpStatus.OK);
		}
		return new ResponseEntity<UsuarioDTO>( HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping("/usuario/list")
	public ResponseEntity<Collection<UsuarioDTO>> recuperarUsuarios() {
		return new ResponseEntity<Collection<UsuarioDTO>>(this.usuarioService.recuperarUsuariosPublico(), HttpStatus.OK);
	}

	@RequestMapping("/usuario/campanha/doacao/list/{email}")
	public ResponseEntity<Collection<Campanha>> recuperaCampanhasDoadas(@PathVariable String email, @RequestBody(required = false) String subCampanha){
		Collection<Campanha> campanhasDoadas = this.usuarioService.recuperaCampanhasDoadasUsuario(email,subCampanha);
		if(campanhasDoadas != null) {
			return new ResponseEntity<Collection<Campanha>>(campanhasDoadas, HttpStatus.OK);
		}
		return new ResponseEntity<Collection<Campanha>> (HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping("/usuario/campanha/criada/list/{email}")
	public ResponseEntity<Collection<Campanha>> recuperaCampanhasUsuario(@PathVariable String email, @RequestBody(required = false) String subCampanha){
		Collection<Campanha> campanhasCriadas = this.usuarioService.recuperaCampanhasCriadasUsuario(email, subCampanha);
		if(campanhasCriadas != null) {
			return new ResponseEntity<Collection<Campanha>>(campanhasCriadas, HttpStatus.OK);
		}
		return new ResponseEntity<Collection<Campanha>> (HttpStatus.NOT_FOUND);
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
