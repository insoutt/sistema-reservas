package com.betancourt.reservas.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.betancourt.reservas.dao.IReservacion;
import com.betancourt.reservas.entities.Reservacion;

@Service
public class ReservacionService implements IReservacionService {
	@Autowired
	private IReservacion dao;
	
	@Override
	@Transactional
	public void save(Reservacion entity) {
		dao.save(entity);
	}

	@Override
	public Reservacion findById(Integer id) {
		return dao.findById(id).get();
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		dao.deleteById(id);
	}

	@Override
	public List<Reservacion> findAll() {
		return (List<Reservacion>) dao.findAll();
	}
}
