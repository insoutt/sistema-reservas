package com.betancourt.reservas.dao;

import org.springframework.data.repository.CrudRepository;

import com.betancourt.reservas.entities.Gerente;

public interface IGerente extends CrudRepository<Gerente, Integer>{

}
