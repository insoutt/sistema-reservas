package com.betancourt.reservas.services;

import java.util.List;

import com.betancourt.reservas.entities.Cliente;
import com.betancourt.reservas.entities.Gerente;

public interface IGerenteService {
	public void save(Gerente entity);
	public Gerente findById(Integer id);
	public void delete(Integer id);
	public Gerente findByEmail(String email);
	public List<Gerente> findAll();
}
