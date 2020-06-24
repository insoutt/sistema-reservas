package com.betancourt.reservas.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.betancourt.reservas.dao.IServicio;
import com.betancourt.reservas.entities.Servicio;

@Service
public class ServicioService implements IServicioService {
	@Autowired
	private IServicio dao;
	
	@Override
	@Transactional
	public void save(Servicio entity) {
		dao.save(entity);
	}

	@Override
	public Servicio findById(Integer id) {
		return dao.findById(id).get();
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		dao.deleteById(id);
	}

	@Override
	public List<Servicio> findAll() {
		return (List<Servicio>) dao.findAll();
	}
}
