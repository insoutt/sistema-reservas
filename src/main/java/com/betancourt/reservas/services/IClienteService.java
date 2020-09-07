package com.betancourt.reservas.services;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.betancourt.reservas.entities.Cliente;

public interface IClienteService {
	public void save(Cliente entity);
	public Cliente findById(Integer id);
	
	public Cliente findByEmail(String email);
	
	public void delete(Integer id);
	public List<Cliente> findAll();
}
