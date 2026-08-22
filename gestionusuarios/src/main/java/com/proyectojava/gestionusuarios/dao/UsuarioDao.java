package com.proyectojava.gestionusuarios.dao;

import java.util.List;

import com.proyectojava.gestionusuarios.models.Usuario;

public interface UsuarioDao {

	Usuario getUsuario(Long id);

	List<Usuario> getUsuarios();

	void deleteUsuario(Long id);


}
