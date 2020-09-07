package com.betancourt.reservas.reporting;

import java.io.Serializable;
import java.math.BigInteger;

public class RptReservacionesServicio implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String servicio;
	private BigInteger total;
	
	
	public RptReservacionesServicio() {
		super();
	}
	
	public RptReservacionesServicio(BigInteger total, String servicio) {
		super();
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
}
