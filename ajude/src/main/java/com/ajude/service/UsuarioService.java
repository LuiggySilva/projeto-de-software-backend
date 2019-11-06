package com.ajude.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ajude.DAO.UsuarioDAO;
import com.ajude.model.Usuario;

import javax.servlet.ServletException;



@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioDAO<Usuario, String> usuariosDAO;
	@Autowired
	private  JWTService jwtService;

	public UsuarioService() {
	}

	public Usuario cadastrarUsuario(Usuario u) {
		Usuario user = this.recuperarUsuario(u.getEmail());
		if(!(user == null)) { 			//usuario ja existe
			return user;
		}
		else {
			this.usuariosDAO.save(u);
			return this.recuperarUsuario(u.getEmail());
		}			
	}

	public Usuario recuperaUsuarioToken(String token){
		String email;
		Usuario usuario;
		try {
			email = jwtService.getSujeitoDoToken(token);
		}catch(ServletException e){
			return null;
		}
		return this.recuperarUsuario(email);

	}
	
	public Usuario recuperarUsuario(String email) {
		try {
			Usuario user = usuariosDAO.findById(email).get();
			return user;
		} catch (Exception e) {
			return null;
		}
	}

	public boolean verificaUsuario(String email, String senha) {
		Usuario u = recuperarUsuario(email);
		if(u == null || !u.getSenha().equals(senha)) {
			return false;
		}
		else {
			return true;
		}
	}

	public Usuario removeUsuario(String email) {
		Usuario u;
		try {
			u = usuariosDAO.findById(email).get();
			usuariosDAO.deleteById(email);
			return u;
		} catch (Exception e) {
			return null;
		}
	}

	public Collection<Usuario> recuperarUsuarios() {
		return this.usuariosDAO.findAll();
	}
	
}
