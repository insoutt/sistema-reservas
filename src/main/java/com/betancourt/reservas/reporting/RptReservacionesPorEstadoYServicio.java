package com.betancourt.reservas.reporting;

import java.io.Serializable;
import java.math.BigInteger;

public class RptReservacionesPorEstadoYServicio implements Serializable {
	
	private static final long serialVersionUID = 1L;
	

	private String estado;
	private String servicio;
	private BigInteger total;
	
	
	public RptReservacionesPorEstadoYServicio() {
		super();
	}
	
	public RptReservacionesPorEstadoYServicio(String estado, String servicio, BigInteger total) {
		super();
		this.setEstado(estado);
		this.servicio = servicio;
		this.total = total;
	}
	
	public String getServicio() {
		return servicio;
	}

	public void setServicio(String servicio) {
		this.servicio = servicio;
	}

	public BigInteger getTotal() {
		return total;
	}
	public void setTotal(BigInteger total) {
		this.total = total;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
}
