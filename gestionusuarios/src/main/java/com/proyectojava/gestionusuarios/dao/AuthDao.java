package com.proyectojava.gestionusuarios.dao;

import com.proyectojava.gestionusuarios.models.Usuario;

public interface AuthDao {

	// registra un nuevo usuario en la base de datos
	void createUsuario(Usuario usuario);

	Usuario obtenerUsuarioPorCredenciales(String email, String password);
	
}
