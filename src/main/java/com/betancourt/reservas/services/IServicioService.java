package com.betancourt.reservas.services;

import java.util.List;

import com.betancourt.reservas.entities.Reservacion;
import com.betancourt.reservas.entities.Servicio;

public interface IServicioService {
	public void save(Servicio entity);
	public Servicio findById(Integer id);
	public void delete(Integer id);
	public List<Servicio> findAll();
}
