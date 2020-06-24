package com.betancourt.reservas.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.betancourt.reservas.dao.IGerente;
import com.betancourt.reservas.entities.Gerente;

@Service
public class GerenteService implements IGerenteService {
	@Autowired
	private IGerente dao;
	
	@Override
	@Transactional
	public void save(Gerente entity) {
		dao.save(entity);
	}

	@Override
	public Gerente findById(Integer id) {
		return dao.findById(id).get();
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		dao.deleteById(id);
	}

	@Override
	public List<Gerente> findAll() {
		return (List<Gerente>) dao.findAll();
	}
}
