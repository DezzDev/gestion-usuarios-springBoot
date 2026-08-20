package com.proyectojava.gestionusuarios.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.proyectojava.gestionusuarios.models.Usuario;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
@Transactional
public class UsuarioDaoImp implements UsuarioDao {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public Usuario getUsuario(Long id){
		return entityManager.find(Usuario.class, id);
	}

	@Override
	public List<Usuario> getUsuarios() {
		// nombre de la clase Usuario, no de la tabla
		String query = "FROM Usuario";
		return entityManager.createQuery(query, Usuario.class).getResultList();
	}

	@Override
	public void deleteUsuario(Long id){
		
		entityManager.remove(entityManager.find(Usuario.class, id));
	}

	@Override
	public void createUsuario(Usuario usuario){
		entityManager.merge(usuario);
	}

}
