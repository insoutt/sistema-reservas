package com.betancourt.reservas.dao;

import org.springframework.data.repository.CrudRepository;

import com.betancourt.reservas.entities.Usuario;


public interface IUsuario extends CrudRepository<Usuario, Integer> {
	public Usuario findByNombre(String nombre);	
}
