package com.proyectojava.gestionusuarios.dao;

import com.proyectojava.gestionusuarios.models.Usuario;

public interface AuthDao {

	void createUsuario(Usuario usuario);

	Usuario obtenerUsuarioPorCredenciales(String email, String password);
	
}
