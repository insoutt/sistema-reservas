package com.betancourt.reservas.dao;

import org.springframework.data.repository.CrudRepository;
import com.betancourt.reservas.entities.Servicio;

public interface IServicio extends CrudRepository<Servicio, Integer>{

}
