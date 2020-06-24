package com.betancourt.reservas.dao;

import org.springframework.data.repository.CrudRepository;

import com.betancourt.reservas.entities.Cliente;

public interface ICliente extends CrudRepository<Cliente, Integer>{

}
