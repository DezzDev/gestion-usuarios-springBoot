package com.proyectojava.gestionusuarios.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.proyectojava.gestionusuarios.dao.AuthDao;
import com.proyectojava.gestionusuarios.dto.LoginRequest;
import com.proyectojava.gestionusuarios.models.Usuario;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

@RestController
public class AuthController {

	private final AuthDao authDao;

	AuthController(AuthDao authDao) {
		this.authDao = authDao;

	}

	@PostMapping("api/registrar")
	public void createUsuario(@RequestBody Usuario usuario) {

		Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
		String hashedPassword = argon2.hash(1, 1024, 1, usuario.getPassword().getBytes());
		usuario.setPassword(hashedPassword);
		
		authDao.createUsuario(usuario);
	}

	@PostMapping("api/login")
	public String verificarCredenciales(@RequestBody LoginRequest loginRequest) {
		Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
		String hashedPassword = argon2.
		System.out.println("Email: " + loginRequest.getEmail());
		System.out.println("Hashed Password: " + hashedPassword);
		boolean credencialesValidas = authDao.verificarCredenciales(loginRequest.getEmail(), hashedPassword);
		return credencialesValidas ? "ok" : "error";
	}
}
