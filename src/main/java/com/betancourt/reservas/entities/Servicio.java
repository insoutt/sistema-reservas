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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "servicios")
public class Servicio implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name="pk_servicio")
	private Integer idServicio;
	
	@JoinColumn(name = "fk_gerente", referencedColumnName = "pk_gerente")
	@ManyToOne
	private Gerente gerente;
	
	@OneToMany(mappedBy = "servicio", fetch = FetchType.LAZY)
	private List<Reservacion> reservaciones;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="imagen")
	private String imagen;
	
	@Column(name="descripcion")
	private String descripcion;
	
	@Column(name="horario")
	private String horario;
	
	@Column(name="calle_principal")
	private String callePrincipal;
	
	@Column(name="calle_secundaria")
	private String calleSecundaria;

	
	public Servicio() {
		super();
	}
	
	public Servicio(Integer id) {
		super();
		this.idServicio = id;
	}

	public Integer getIdServicio() {
		return idServicio;
	}

	public void setIdServicio(Integer idServicio) {
		this.idServicio = idServicio;
	}

	public Gerente getGerente() {
		return gerente;
	}

	public void setGerente(Gerente gerente) {
		this.gerente = gerente;
	}

	public List<Reservacion> getReservaciones() {
		return reservaciones;
	}

	public void setReservaciones(List<Reservacion> reservaciones) {
		this.reservaciones = reservaciones;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
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

	@Override
	public String toString() {
		return "Servicio " + nombre;
	}
	
	
}
