package com.betancourt.reservas.controllers;

import java.util.List;

import javax.validation.Valid;
import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.betancourt.reservas.entities.Cliente;
import com.betancourt.reservas.entities.Gerente;
import com.betancourt.reservas.entities.Reservacion;
import com.betancourt.reservas.entities.Servicio;
import com.betancourt.reservas.services.IClienteService;
import com.betancourt.reservas.services.IReservacionService;
import com.betancourt.reservas.services.IServicioService;


@Controller
@RequestMapping(value="/reservacion")

public class ReservacionController {
	@Autowired
	private IReservacionService srvReservacion;
	
	@Autowired
	private IServicioService srvServicio;
	
	@Autowired
	private IClienteService srvCliente;
	
	@GetMapping(value="/create")
	public String create(Model model) {
		Reservacion reservacion = new Reservacion();
		model.addAttribute("reservacion", reservacion);
		model.addAttribute("title", "Registro Nueva Reservacion");
		return "reservacion/form";
	}

	
	@PostMapping(value="/save")
	public String save(@RequestBody @Valid Reservacion reservacion, Model model) {
		
		try {
			Servicio servicio = srvServicio.findById(reservacion.getServicioId());
			Cliente cliente = srvCliente.findById(reservacion.getClienteId());
			reservacion.setCliente(cliente);
			reservacion.setServicio(servicio);
			srvReservacion.save(reservacion);
			return "redirect:/reservacion/list";
		} catch (Exception e) {
			e.printStackTrace();
			throw new ValidationException("Campos no válidos ");
		}
	}
	
	
	@GetMapping(value="/retrieve/{id}")
	public String retrieve(@PathVariable(value="id") Integer id, Model model) {
		Reservacion reservacion = srvReservacion.findById(id);
		model.addAttribute("reservacion", reservacion);
		
		
		model.addAttribute("servicio", reservacion.getServicio());
		
		model.addAttribute("title", "Ver reservación");
		return "reservacion/card";
	}
	
	@GetMapping(value="/update/{id}")
	public String update(@PathVariable(value="id") Integer id, Model model) {
		Reservacion reservacion = srvReservacion.findById(id);
		model.addAttribute("reservacion", reservacion);
		model.addAttribute("title", "Actualizando el registro de " + reservacion.toString());
		return "reservacion/form";
	}
	
	@GetMapping(value="/cancel/{id}")
	public String cancel(@PathVariable(value="id") Integer id, Model model) {
		Reservacion reservacion = srvReservacion.findById(id);
		reservacion.setEstado("Cancelar");
		model.addAttribute("reservacion", reservacion);
		model.addAttribute("title", "Ver reservación");
		srvReservacion.save(reservacion);
		return "reservacion/card";
	}
	
	@GetMapping(value="/attend/{id}")
	public String attend(@PathVariable(value="id") Integer id, Model model) {
		Reservacion reservacion = srvReservacion.findById(id);
		reservacion.setEstado("Atendido");
		model.addAttribute("reservacion", reservacion);
		model.addAttribute("title", "Ver reservación");
		srvReservacion.save(reservacion);
		return "reservacion/card";
	}
	
	@PostMapping(value="/delete/{id}")
	public String delete(@PathVariable(value="id") Integer id, Model model) {
		srvReservacion.delete(id);
		return "redirect:/reservacion/list";
	}
	
	@GetMapping(value="/list")
	public String list(Model model) {
		List<Reservacion> reservaciones = srvReservacion.findAll();
		model.addAttribute("title", "Listado de reservaciones");
		model.addAttribute("reservaciones", reservaciones);
		return "reservacion/list";
	}
}
