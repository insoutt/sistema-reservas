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

@Entity
@Table(name="gerentes")
public class Gerente extends Persona implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name="pk_gerente")
	private Float idGerente;
	
	
	@OneToMany(mappedBy = "gerente", fetch = FetchType.LAZY)
	private List<Servicio> servicios;
	
	@Column(name="especializacion")
	private String especializacion;
	
	@Column(name="descripcion")
	private String descripcion;

	public Gerente() {
		super();
	}
	
	public Gerente(Float id) {
		super();
		this.idGerente = id;
	}

	public Float getIdGerente() {
		return idGerente;
	}

	public void setIdGerente(Float idGerente) {
		this.idGerente = idGerente;
	}

	public List<Servicio> getServicios() {
		return servicios;
	}

	public void setServicios(List<Servicio> servicios) {
		this.servicios = servicios;
	}

	public String getEspecializacion() {
		return especializacion;
	}

	public void setEspecializacion(String especializacion) {
		this.especializacion = especializacion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
