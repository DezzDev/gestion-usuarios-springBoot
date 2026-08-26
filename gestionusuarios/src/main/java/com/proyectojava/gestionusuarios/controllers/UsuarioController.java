package com.proyectojava.gestionusuarios.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.proyectojava.gestionusuarios.dao.UsuarioDao;
import com.proyectojava.gestionusuarios.models.Usuario;
import com.proyectojava.gestionusuarios.utils.JWTUtil;

import io.jsonwebtoken.Claims;

@RestController
public class UsuarioController {

	private final UsuarioDao usuarioDao;
	private final JWTUtil jwtUtil;

	// Inyecta la dependencia de UsuarioDao para poder acceder a los métodos de la
	// capa de persistencia
	// mediante el constructor de la clase UsuarioController
	UsuarioController(UsuarioDao usuarioDao, JWTUtil jwtUtil) {
		this.usuarioDao = usuarioDao;
		this.jwtUtil = jwtUtil;
	}

	private boolean validarToken(String token) {
		try {
			Claims claims = jwtUtil.validate(token);
			if (claims != null) {
				return true;
			}
		} catch (Exception e) {
			System.out.println("Error al validar el token: " + e.getMessage());
		}
		return false;
	}

	@GetMapping("api/usuarios/{id}")
	public Usuario getUsuario(@PathVariable Long id) {
		return usuarioDao.getUsuario(id);
	}

	@GetMapping("api/usuarios")
	public List<Usuario> getUsuarios(@RequestHeader(value = "Authorization", required = true) String token) {
		if (!validarToken(token)) {
			return null;
		}
		return usuarioDao.getUsuarios();
	}

	@DeleteMapping("api/usuarios/{id}")
	public void deleteUsuario(@PathVariable Long id,
			@RequestHeader(value = "Authorization", required = true) String token) {
		if (!validarToken(token)) {
			return;
		}
		usuarioDao.deleteUsuario(id);
	}

}