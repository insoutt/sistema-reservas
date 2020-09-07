package com.betancourt.reservas.services;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ResponseBody;

import com.betancourt.reservas.dao.IReservacion;
import com.betancourt.reservas.entities.Reservacion;
import com.betancourt.reservas.reporting.RptReservacionesServicio;

@Service
public class ReservacionService implements IReservacionService {
	@Autowired
	private IReservacion dao;
	
	@PersistenceContext
	private EntityManager em;
	
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
	
	public List<RptReservacionesServicio> rptReservacionesServicio() {
		StoredProcedureQuery query = em.createStoredProcedureQuery("reservaciones_por_servicio");
		query.execute();
		List<Object[]> datos = query.getResultList();		
		return datos.stream()
				.map(r -> new RptReservacionesServicio((BigInteger)r[0], (String)r[1]))
				.collect(Collectors.toList());		
	}
	
	public List<RptReservacionesServicio> rptReservacionesEstado(String estado) {
		StoredProcedureQuery query = em.createStoredProcedureQuery("reservaciones_por_estado");
		query.registerStoredProcedureParameter("pr_estado", String.class, ParameterMode.IN);
		query.setParameter("pr_estado", estado);
		query.execute();
		List<Object[]> datos = query.getResultList();		
		return datos.stream()
				.map(r -> new RptReservacionesServicio((BigInteger)r[0], (String)r[1]))
				.collect(Collectors.toList());		
	}

}
