package com.betancourt.reservas.services;

import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ResponseBody;

import com.betancourt.reservas.entities.Reservacion;
import com.betancourt.reservas.reporting.RptReservacionesPorEstadoYServicio;
import com.betancourt.reservas.reporting.RptReservacionesServicio;

public interface IReservacionService {
	public void save(Reservacion entity);
	public Reservacion findById(Integer id);
	public void delete(Integer id);
	public List<Reservacion> findAll();
	public List<RptReservacionesServicio> rptReservacionesServicio();
	public List<RptReservacionesServicio> rptReservacionesEstado(String estado);
	public List<RptReservacionesPorEstadoYServicio> rptReservacionesPorEstadoYServicio();
}
