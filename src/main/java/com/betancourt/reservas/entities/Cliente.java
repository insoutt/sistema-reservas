package com.betancourt.reservas.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name="clientes")
public class Cliente extends Persona implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name="pk_cliente")
	private Integer idCliente;
	
	@OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
	private List<Reservacion> reservaciones;
	
	@NotEmpty
	@Size(max = 40)
	@Column(name="calle_principal")
	private String callePrincipal;
	
	@NotEmpty
	@Size(max = 40)
	@Column(name="calle_secundaria")
	private String calleSecundaria;
	
	@NotEmpty
	@Size(max = 40)
	@Column(name="nro_casa")
	private String nroCasa;
	
	
	public Cliente() {
		super();
	}

	public Cliente(Integer idCliente) {
		super();
		this.idCliente = idCliente;
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
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
