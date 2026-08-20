package com.proyectojava.gestionusuarios.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

// especifica que esta clase es una entidad de la base de datos de la tabla usuarios
@Entity
@Table(name = "usuarios")
@ToString
@EqualsAndHashCode
public class Usuario {

	@Getter @Setter @Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Getter @Setter @Column(name = "nombre")
	private String nombre;

	@Getter @Setter @Column(name = "apellido")
	private String apellido;

	@Getter @Setter @Column(name = "telefono")
	private int telefono;

	@Getter @Setter @Column(name = "email")
	private String email;

	@Getter @Setter @Column(name = "password")
	private String password;

	public Usuario(){

	};
	
	public Usuario(Long id, String nombre, String apellido, int telefono, String email, String password) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.telefono = telefono;
		this.email = email;
		this.password = password;
	}

	
	
}
