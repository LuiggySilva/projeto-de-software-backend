package com.ajude.DAO;

import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ajude.model.Usuario;

@Repository
public interface UsuarioDAO<T, ID extends Serializable> extends JpaRepository<Usuario, String> {

}
