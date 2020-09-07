package com.betancourt.reservas.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.betancourt.reservas.entities.Cliente;

public interface ICliente extends CrudRepository<Cliente, Integer>{
	@Query("SELECT M FROM Cliente M WHERE M.email= :email")	
	public Cliente findByEmail(String email);
}
