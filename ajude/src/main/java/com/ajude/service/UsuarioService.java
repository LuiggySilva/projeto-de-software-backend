package com.ajude.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ajude.DAO.UsuarioDAO;
import com.ajude.model.Usuario;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioDAO<Usuario, String> usuario;

	public Usuario cadastrarUsuario(Usuario u) {
		try {
			Usuario user = usuario.findById(u.getEmail()).get();
			return user;
		} catch (Exception e) {
			this.usuario.save(u);
			return this.usuario.findById(u.getEmail()).get();
		}
	}
	
	public Usuario recuperarUsuario(String email) {
		try {
			Usuario user = usuario.findById(email).get();
			return user;
		} catch (Exception e) {
			return null;
		}
	}
	
}
