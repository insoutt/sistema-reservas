package com.betancourt.reservas.dao;

import org.springframework.data.repository.CrudRepository;

import com.betancourt.reservas.entities.Reservacion;

public interface IReservacion extends CrudRepository<Reservacion, Integer>{

}
