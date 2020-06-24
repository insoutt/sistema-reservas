package com.betancourt.reservas.services;

import java.util.List;

import com.betancourt.reservas.entities.Cliente;

public interface IClienteService {
	public void save(Cliente entity);
	public Cliente findById(Integer id);
	public void delete(Integer id);
	public List<Cliente> findAll();
}
