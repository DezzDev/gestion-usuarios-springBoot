package com.proyectojava.gestionusuarios.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.proyectojava.gestionusuarios.dao.AuthDao;
import com.proyectojava.gestionusuarios.dto.LoginRequest;
import com.proyectojava.gestionusuarios.models.Usuario;
import com.proyectojava.gestionusuarios.utils.JWTUtil;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

@RestController
public class AuthController {

	private final AuthDao authDao;
	private final JWTUtil jwtUtil;

	AuthController(AuthDao authDao, JWTUtil jwtUtil) {
		this.authDao = authDao;
		this.jwtUtil = jwtUtil;
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

		Usuario userLogueado = authDao.obtenerUsuarioPorCredenciales(loginRequest.getEmail(), loginRequest.getPassword());
		
		if(userLogueado == null){
			return "error";
		}
		String token =  jwtUtil.create(userLogueado.getId().toString(), userLogueado.getEmail());
		return token;
	}
}
