package com.proyectojava.gestionusuarios.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.proyectojava.gestionusuarios.models.Usuario;

@RestController
public class UsuarioController {

	@GetMapping("usuario/{id}")
	public Usuario getUsuario(@PathVariable Long id) {

		Usuario usuario1 = new Usuario(
				id,
				"Daniel",
				"Zapata",
				123456,
				"daniel@email.com",
				"password");

		return usuario1;
	}
	
	@GetMapping("usuarios")
	public List<Usuario> getUsuarios() {
		return Arrays.asList(
			new Usuario(1L, "Daniel", "Zapata", 123456, "daniel@email.com", "password"),
			new Usuario(2L, "María", "Gómez", 789012, "maria@email.com", "password"),
			new Usuario(3L, "Juan", "Pérez", 345678, "juan@email.com", "password"),
			new Usuario(4L, "Laura", "Rodríguez", 901234, "laura@email.com", "password")
		);
	}



}
