package com.betancourt.reservas.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.betancourt.reservas.dao.ICliente;
import com.betancourt.reservas.entities.Cliente;

@Service
public class ClienteService implements IClienteService {
	@Autowired
	private ICliente dao;
	
	@Override
	@Transactional
	public void save(Cliente entity) {
		dao.save(entity);
	}

	@Override
	public Cliente findById(Integer id) {
		return dao.findById(id).get();
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		dao.deleteById(id);
	}

	@Override
	public List<Cliente> findAll() {
		return (List<Cliente>) dao.findAll();
	}
}
