package com.betancourt.reservas.entities;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@MappedSuperclass
abstract class Persona {
	
	@NotEmpty
	@Size(max = 35)
	@Column(name="nombres")
	private String nombres;
	
	@Column(name="apellidos")
	@NotEmpty
	@Size(max = 35)
	private String apellidos;
	
	@NotEmpty
	@Size(max = 10)
	@Column(name="cedula")
	private String cedula;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name="fecha_nacimiento")
	private Calendar fechaNacimiento;
	
	@NotEmpty
	@Size(max = 60)
	@Column(name="email")
	private String email;
	
	@NotEmpty
	@Size(max = 15)
	@Column(name="telefono")
	private String telefono;
	
	@NotEmpty
	@Size(max = 20)
	@Column(name="genero")
	private String genero;
	
	
	public Persona() {
		super();
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public Calendar getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Calendar fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String correoElectronico) {
		this.email = correoElectronico;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}
	
	public String fechaNac() {
		if (this.fechaNacimiento == null)
			return "-";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy");
		return sdf.format(fechaNacimiento.getTime());
	}
}