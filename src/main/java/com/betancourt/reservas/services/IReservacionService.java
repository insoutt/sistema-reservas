package com.betancourt.reservas.services;

import java.util.List;

import com.betancourt.reservas.entities.Reservacion;

public interface IReservacionService {
	public void save(Reservacion entity);
	public Reservacion findById(Integer id);
	public void delete(Integer id);
	public List<Reservacion> findAll();
}
