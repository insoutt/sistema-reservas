package com.betancourt.reservas.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "gerente")
public class Gerente extends Persona implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@OneToMany(mappedBy = "gerente", fetch = FetchType.LAZY)
	private List<Servicio> servicios;
	
	@Column(name="tipo_de_servicios")
	private String tipoDeServicios;
	
	@Column(name="descripcion")
	private String descripcion;
	
	@Column(name = "especializacion")
	private String especializacion;

	public Gerente() {
		super();
	}
	
	public Gerente(Integer id) {
		super();
		this.setIdPersona(id);
	}


	public List<Servicio> getServicios() {
		return servicios;
	}

	public void setServicios(List<Servicio> servicios) {
		this.servicios = servicios;
	}

	public String getTipoDeServicios() {
		return tipoDeServicios;
	}

	public void setTipoDeServicios(String tipoDeServicios) {
		this.tipoDeServicios = tipoDeServicios;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
	public String getEspecializacion() {
		return especializacion;
	}

	public void setEspecializacion(String especializacion) {
		this.especializacion = especializacion;
	}
}
