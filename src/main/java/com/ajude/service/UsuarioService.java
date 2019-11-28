package com.ajude.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.ajude.DAO.CampanhaDAO;
import com.ajude.DAO.DoacaoDAO;
import com.ajude.model.Campanha;
import com.ajude.model.Doacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.ajude.DAO.UsuarioDAO;
import com.ajude.model.Usuario;
import com.ajude.model.UsuarioDTO;
import com.ajude.util.JavaMailApp;
import com.ajude.util.OrdenaCampanhaDataCriacao;

import javax.servlet.ServletException;



@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioDAO<Usuario, String> usuariosDAO;
	@Autowired
	private  JWTService jwtService;
	@Autowired
	private DoacaoDAO<Doacao,Long> doacoesDAO;
	@Autowired
	private CampanhaService campanhaService;
	@Autowired
	private CampanhaDAO<Campanha ,Long> campanhasDAO;
	public UsuarioService() {
	}

	public Usuario cadastrarUsuario(Usuario u) {
		Usuario user = this.recuperarUsuario(u.getEmail());
		if(!(user == null)) { 			
			return user;
		}
		else {
			this.usuariosDAO.save(u);
			JavaMailApp.sendEmail(u.getEmail());
			return this.recuperarUsuario(u.getEmail());
		}			
	}

	public Usuario recuperaUsuarioToken(String token){
		String email;
		try {
			email = jwtService.getSujeitoDoToken(token);
		}catch(ServletException e){
			return null;
		}
		return this.recuperarUsuario(email);

	}
	
	public UsuarioDTO recuperaUsuarioDTOToken(String token){
		String email;
		try {
			email = jwtService.getSujeitoDoToken(token);
		}catch(ServletException e){
			return null;
		}
		Usuario u = this.recuperarUsuario(email);
		return new UsuarioDTO(u.getNome(), u.getSobrenome(), u.getEmail());

	}
	
	public Usuario recuperarUsuario(String email) {
		try {
			Usuario user = usuariosDAO.findById(email).get();
			return user;
		} catch (Exception e) {
			return null;
		}
	}

	public UsuarioDTO recuperarUsuarioPublico(String email) {
		try {
			Usuario user = usuariosDAO.findById(email).get();

			return  new UsuarioDTO(user.getNome(), user.getSobrenome(), user.getEmail());
		} catch (Exception e) {
			return null;
		}
	}
	
	public Collection<UsuarioDTO> recuperarUsuariosPublico() {
		List<UsuarioDTO> saida = new ArrayList<>(); 
		for(Usuario user : this.usuariosDAO.findAll()) {
			 saida.add(new UsuarioDTO(user.getNome(), user.getSobrenome(), user.getEmail()));
		 }
		return saida;
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


	public Campanha fazerDoacaoCampanha(String token , long idCampanha,Doacao doacao){
		Usuario usuario = recuperaUsuarioToken(token);
		Campanha campanha = campanhaService.recuperaCampanha(idCampanha);
		if(campanha != null && usuario != null) {
			try {
				Doacao novaDoacao = new Doacao(campanha, usuario, doacao.getDoacao());
				campanha.addDoacao(doacao.getDoacao());
				campanhaService.salvarDoacao(novaDoacao);
				return this.campanhasDAO.findById(campanha.getId()).get();
			}catch (Exception e){
				return null;
			}
		}return  null;
	}

	public Collection<Campanha> recuperaCampanhasDoadasUsuario(String email, String campanhaSub){
		Usuario usuario = this.recuperarUsuario(email);
		ArrayList<Campanha> listaCampanhas = new ArrayList<Campanha>();
		if(usuario != null){
			listaCampanhas = new ArrayList<Campanha>();
			for (Doacao doacao :usuario.getListaDoacoes()){
				Campanha c = doacao.getCampanha();
				if(campanhaSub != null){
					if(c.getNomeCurto().toLowerCase().contains((campanhaSub).toLowerCase())
					|| c.getDescricao().toLowerCase().contains((campanhaSub).toLowerCase())){
						listaCampanhas.add(c);
					}
				}else {
					listaCampanhas.add(c);
				}	
			}
			Collections.sort(listaCampanhas,new OrdenaCampanhaDataCriacao());
			return listaCampanhas;
		}
		return  null;
	}
	
	public Collection<Campanha> recuperaCampanhasCriadasUsuario(String email, String campanhaSub){
		Usuario usuario = this.recuperarUsuario(email);
		ArrayList<Campanha> listaCampanhas = new ArrayList<Campanha>();
		if(usuario != null){
			for (Campanha c :usuario.getMinhasCampanhas()){
				if(campanhaSub != null){
					if(c.getNomeCurto().contains(campanhaSub) || c.getDescricao().contains(campanhaSub)) {
						listaCampanhas.add(c);
					}
				}else {
					listaCampanhas.add(c);
				}	
			}
			Collections.sort(listaCampanhas, new OrdenaCampanhaDataCriacao());
			return listaCampanhas;
		}else {
			return null;
		}
	}

	public Collection<Usuario> recuperarUsuarios() {
		return this.usuariosDAO.findAll();
	}
	
}

