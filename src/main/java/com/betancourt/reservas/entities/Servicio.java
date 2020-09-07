package com.betancourt.reservas.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "servicios")
public class Servicio implements Serializable {

	private static final long serialVersionUID = 1L;

	@Transient
	private int gerenteId;
	
	public int getGerenteId() {
		return gerenteId;
	}

	public void setGerenteId(int gerenteId) {
		this.gerenteId = gerenteId;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name="pk_servicio")
	private Integer idServicio;
	
	@JoinColumn(name = "fk_gerente", referencedColumnName = "pk_gerente")
	@ManyToOne
	private Gerente gerente;
	
	
	@OneToMany(mappedBy = "servicio", fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	private List<Reservacion> reservaciones;
	
	@NotEmpty
	@Size(max = 70)
	@Column(name="nombre")
	private String nombre;

	
	@NotEmpty
	@Size(max = 200)
	@Column(name="descripcion")
	private String descripcion;
	
	@NotEmpty
	@Size(max = 90)
	@Column(name="calle_principal")
	private String callePrincipal;
	
	@NotEmpty
	@Size(max = 90)
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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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
