package com.proyectojava.gestionusuarios.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.proyectojava.gestionusuarios.dao.UsuarioDao;
import com.proyectojava.gestionusuarios.models.Usuario;


@RestController
public class UsuarioController {

	private final UsuarioDao usuarioDao;

	// Inyecta la dependencia de UsuarioDao para poder acceder a los métodos de la capa de persistencia
	// mediante el constructor de la clase UsuarioController
	UsuarioController(UsuarioDao usuarioDao) {
		this.usuarioDao = usuarioDao;
	}

	@GetMapping("api/usuarios/{id}")
	public Usuario getUsuario(@PathVariable Long id) {
		return usuarioDao.getUsuario(id);
	}
	
	@GetMapping("api/usuarios")
	public List<Usuario> getUsuarios() {
		return usuarioDao.getUsuarios();
	}

	@DeleteMapping("api/usuarios/{id}")
	public void deleteUsuario(@PathVariable Long id) {
		usuarioDao.deleteUsuario(id);
	}


	

}
