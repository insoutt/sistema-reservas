package com.betancourt.reservas.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "cliente")
public class Cliente extends Persona implements Serializable {

	private static final long serialVersionUID = 1L;

	@OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
	private List<Reservacion> reservaciones;
	
	@Column(name="calle_principal")
	private String callePrincipal;
	
	@Column(name="calle_secundaria")
	private String calleSecundaria;
	
	@Column(name="nro_casa")
	private String nroCasa;
	
	
	public Cliente() {
		super();
	}

	public Cliente(Integer idCliente) {
		super();
		this.setIdPersona(idCliente);
	}

	public List<Reservacion> getReservaciones() {
		return reservaciones;
	}

	public void setReservaciones(List<Reservacion> reservaciones) {
		this.reservaciones = reservaciones;
	}

	public String getCallePrincipal() {
		return callePrincipal;
	}

	public void setCallePrincipal(String callePrincipal) {
		this.callePrincipal = callePrincipal;
	}

	public String getCalleSecundaria() {
		return calleSecundaria;
	}

	public void setCalleSecundaria(String calleSecundaria) {
		this.calleSecundaria = calleSecundaria;
	}

	public String getNroCasa() {
		return nroCasa;
	}

	public void setNroCasa(String nroCasa) {
		this.nroCasa = nroCasa;
	}
}
