package com.proyectojava.gestionusuarios.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyectojava.gestionusuarios.models.Usuario;



@RestController
public class UsuarioController {

	@GetMapping("usuario")
	public Usuario getUsuario() {

		Usuario usuario1 = new Usuario("Daniel", "Zapata", 123456, "daniel@email.com", "password");

		return usuario1;
	}
	
}
