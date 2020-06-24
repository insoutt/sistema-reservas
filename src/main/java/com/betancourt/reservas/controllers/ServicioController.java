package com.betancourt.reservas.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.betancourt.reservas.entities.Gerente;
import com.betancourt.reservas.entities.Servicio;
import com.betancourt.reservas.services.IGerenteService;
import com.betancourt.reservas.services.IServicioService;

@Controller
@RequestMapping(value="/servicio")

public class ServicioController {
	
	@Autowired
	private IServicioService srvServicio;
	
	@Autowired
	private IGerenteService srvGerente;
	
	@GetMapping(value="/create")
	public String create(Model model) {
		List<Gerente> gerentes = srvGerente.findAll();
		model.addAttribute("gerentes", gerentes);
		
		Servicio servicio = new Servicio();
		model.addAttribute("servicio", servicio);
		model.addAttribute("title", "Registro Nuevo Servicio");
		return "servicio/form";
	}
	
	@PostMapping(value="/save")
	public String save(Servicio servicio, Model model) {
		srvServicio.save(servicio);
		return "redirect:/servicio/list";
	}
	
	@GetMapping(value="/retrieve/{id}")
	public String retrieve(@PathVariable(value="id") Integer id, Model model) {
		Servicio servicio = srvServicio.findById(id);
		model.addAttribute("servicio", servicio);
		model.addAttribute("title", "Ver servicio");
		return "servicio/card";
	}
	
	@GetMapping(value="/update/{id}")
	public String update(@PathVariable(value="id") Integer id, Model model) {
		Gerente gerente = new Gerente();
		model.addAttribute("gerente", gerente);
		Servicio servicio = srvServicio.findById(id);
		model.addAttribute("servicio", servicio);
		model.addAttribute("title", "Actualizando el registro de " + servicio.toString());
		return "servicio/form";
	}
	
	@GetMapping(value="/delete/{id}")
	public String delete(@PathVariable(value="id") Integer id, Model model) {
		srvServicio.delete(id);
		return "redirect:/servicio/list";
	}
	
	@GetMapping(value="/list")
	public String list(Model model) {
		List<Servicio> servicios = srvServicio.findAll();
		model.addAttribute("servicios", servicios);
		model.addAttribute("title", "Listado de servicios");
		return "servicio/list";
	}
}
