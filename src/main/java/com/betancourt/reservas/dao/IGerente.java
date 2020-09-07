package com.betancourt.reservas.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.betancourt.reservas.entities.Cliente;
import com.betancourt.reservas.entities.Gerente;

public interface IGerente extends CrudRepository<Gerente, Integer>{
	@Query("SELECT M FROM Gerente M WHERE M.email= :email")	
	public Gerente findByEmail(String email);
}
