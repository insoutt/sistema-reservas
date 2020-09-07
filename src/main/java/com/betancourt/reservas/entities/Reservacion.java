package com.betancourt.reservas.entities;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "reservaciones")
public class Reservacion implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name="pk_reservacion")
	private Integer idReservacion;
	
	@JoinColumn(name = "fk_cliente", referencedColumnName = "pk_cliente")
	@ManyToOne
	private Cliente cliente;
	
	@JoinColumn(name = "fk_servicio", referencedColumnName = "pk_servicio")
	@ManyToOne
	private Servicio servicio;

	@NotEmpty
	@Size(max = 70)
	@Column(name="titulo")
	private String titulo;
	
	@NotEmpty
	@Size(max = 70)
	@Column(name="detalles")
	private String detalles;
	
	@Column(name="fecha")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")	
	private Calendar fecha;
	
	@NotEmpty
	@Size(max = 70)
	@Column(name="hora")
	private String hora;
	
	@NotEmpty
	@Size(max = 70)
	@Column(name="estado")
	private String estado;
	
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Transient
	private int servicioId;
	
	@Transient
	private int clienteId;
	
	public int getClienteId() {
		return clienteId;
	}

	public void setClienteId(int clienteId) {
		this.clienteId = clienteId;
	}

	public int getServicioId() {
		return servicioId;
	}

	public void setGerenteId(int servicioId) {
		this.servicioId = servicioId;
	}

	public Reservacion() {
		super();
	}
	
	public Reservacion(Integer id) {
		super();
		this.idReservacion = id;
	}
	
	public Integer getIdReservacion() {
		return idReservacion;
	}

	public void setIdReservacion(Integer idReservacion) {
		this.idReservacion = idReservacion;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Servicio getServicio() {
		return servicio;
	}

	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDetalles() {
		return detalles;
	}

	public void setDetalles(String detalles) {
		this.detalles = detalles;
	}

	public Calendar getFecha() {
		return fecha;
	}

	public void setFecha(Calendar fecha) {
		this.fecha = fecha;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}
	
	public String fecha() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy");		
		return sdf.format(fecha.getTime());
	}

	@Override
	public String toString() {
		return "Reservacion " + titulo;
	}
}
