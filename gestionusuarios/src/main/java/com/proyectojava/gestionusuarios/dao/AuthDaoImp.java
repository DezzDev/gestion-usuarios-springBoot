package com.proyectojava.gestionusuarios.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.proyectojava.gestionusuarios.models.Usuario;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
@Transactional
public class AuthDaoImp implements AuthDao {

	@PersistenceContext
	EntityManager entityManager;

		@Override
	public void createUsuario(Usuario usuario){
		entityManager.merge(usuario);
	}

	@Override
	public Usuario obtenerUsuarioPorCredenciales(String email, String password) {
		String query = "FROM Usuario WHERE email = :email";
		try {
			Usuario foundUsuario = entityManager.createQuery(query, Usuario.class)
				.setParameter("email", email)
				.getSingleResult();


			Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
			Boolean isVerified = argon2.verify(foundUsuario.getPassword(), password.getBytes());

			if(isVerified){
				return foundUsuario;
			} else {
				return null;
			}


			
		} catch (Exception e) {
			return null;
		}
	}
}

	