package com.ajude.service;

import java.util.ArrayList;
import java.util.Collection;

import com.ajude.model.*;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ajude.DAO.CampanhaDAO;
import com.ajude.DAO.ComentarioDAO;
import com.ajude.DAO.LikeDAO;


@Service
public class CampanhaService {
	
	@Autowired
	private CampanhaDAO<Campanha ,Long> campanhasDAO;
    @Autowired
    private ComentarioDAO<Comentario, Long> comentariosDAO;
    @Autowired
    private LikeDAO<Like, Long> likesDAO;
	@Autowired
	private UsuarioService usuarioService;


	public Campanha cadastrarCampanha(CampanhaDTO c, String token) {
		Usuario user = usuarioService.recuperaUsuarioToken(token);
		if(user != null) {
			Campanha novaCampanha = c.makeCampanha(user);
			campanhasDAO.save(novaCampanha);
			return  novaCampanha;
		}
		else {
			return null;
		}
	}

	public Campanha recuperaCampanha(long id) {
		try {
			Campanha camp = campanhasDAO.findById(id).get();
			ArrayList<Comentario> c = new ArrayList<Comentario>();
			for (Comentario coment : this.comentariosDAO.findAll()) {
				if (coment.getCampanha().equals(camp)) {
					c.add(coment);
				}
			}
			camp.setComentarios(c);
			return camp;
		} catch (Exception e) {
			return null;
		}
	}
	
	public boolean Like(String token, long id) {
		Campanha c = this.recuperaCampanha(id);
		Usuario u = this.usuarioService.recuperaUsuarioToken(token);
		if (c == null || u == null) {
			return false;
		}
		else {
			if(this.verificaLike(u, c)) {
				this.removerLikeCampanha(c, this.getLikeId(u, c));
			}
			else {
				this.darLikeCampanha(u, c);
			}
			return true;
		}
	}
	
	private void darLikeCampanha(Usuario u, Campanha c) {
		c.addLike();
		this.likesDAO.save(new Like(u, c));
		
		this.campanhasDAO.save(c);
	}
	
	private void removerLikeCampanha(Campanha c, long id) {
		c.subLike();
		this.likesDAO.deleteById(id);
		this.campanhasDAO.save(c);
	}
	
	public boolean fazerComentarioCampanha(String token, long id, Comentario comentario) {
		Campanha c = this.recuperaCampanha(id);
		Usuario usuario = usuarioService.recuperaUsuarioToken(token);
		if (usuario == null) {
			return false;
		}
		else {
			Comentario coment = new Comentario(c, usuario, comentario.getComentario());
			this.comentariosDAO.save(coment);
			c.addComentariosCount();
			this.campanhasDAO.save(c);
			return true;
		}
	}
	
	public boolean removerComentarioCampanha(Usuario u, long idCamp, long idComent) {
		Campanha c = this.recuperaCampanha(idCamp);
		if (c == null) {
			return false;
		}
		else {
			this.comentariosDAO.deleteById(idComent);
			c.subComentariosCount();
			this.campanhasDAO.save(c);
			return true;
		}
	}
	
	public boolean darRespostaComentarioCampanha(Usuario u, long idCamp, long idComent,  String resposta) {
		Campanha c = this.recuperaCampanha(idCamp);
		if (c == null) {
			return false;
		}
		else {
			try {
				Comentario coment = this.comentariosDAO.findById(idComent).get();
				coment.setRespostas(new Comentario(c, u, resposta));
				this.comentariosDAO.save(coment);
				c.addComentariosCount();
				this.campanhasDAO.save(c);
				return true;
			} catch (Exception e) {
				return false;
			}
		}
	}
	
	public boolean removerRespostaComentarioCampanha(Usuario u, long idCamp, long idComent, long idRepost) {
		Campanha c = this.recuperaCampanha(idCamp);
		if (c == null) {
			return false;
		}
		else {
			try {
				Comentario coment = this.comentariosDAO.findById(idComent).get();
				coment.removerResposta(idRepost);
				this.comentariosDAO.save(coment);
				c.subComentariosCount();
				this.campanhasDAO.save(c);
				return true;
			} catch (Exception e) {
				return false;
			}
		}
	}
	
	// Tem que ver se precisa referenciar o usuario que doou, acho que sim mas já sao 23:00hr e to cansado vou deixar assim por enquanto
	public boolean fazerDoacaoCampanha(long idCamp, Double valor) {
		Campanha c = recuperaCampanha(idCamp);
		if (c == null) {
			return false;
		}
		else {
			c.setDoacoes(valor);
			this.campanhasDAO.save(c);
			return true;
		}
	}
	
	private boolean verificaLike(Usuario u, Campanha c) {
		for (Like like : this.likesDAO.findAll()) {
			if(like.getUser().equals(u) && like.getCampanha().equals(c)) {
				return true;
			}
		}
		return false;
	}
	
	private long getLikeId(Usuario u, Campanha c) {
		long id = -1;
		for (Like like : this.likesDAO.findAll()) {
			if(like.getUser().equals(u) && like.getCampanha().equals(c)) {
				id = like.getId();
				break;
			}
		}
		return id;
	}

	public Collection<Campanha> recuperarCampanhas() {
		return this.campanhasDAO.findAll();
	}
	
	public Collection<Like> like() {
		return this.likesDAO.findAll();
	}
}
