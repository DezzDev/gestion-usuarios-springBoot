package com.proyectojava.gestionusuarios.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.proyectojava.gestionusuarios.models.Usuario;

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
	public boolean verificarCredenciales(String email, String password) {
		String query = "FROM Usuario WHERE email = :email AND password = :password";
		try {
			Usuario foundUsuario = entityManager.createQuery(query, Usuario.class)
				.setParameter("email", email)
				.setParameter("password", password)
				.getSingleResult();

			return foundUsuario != null;
			
		} catch (Exception e) {
			return false;
		}
	}
}

	