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


@Controller
@RequestMapping(value="/gerente")
public class GerenteController {
	@Autowired
	private IGerenteService srvGerente;
	
	@GetMapping(value="/create")
	public String create(Model model) {
		Gerente gerente = new Gerente();
		model.addAttribute("gerente", gerente);
		model.addAttribute("title", "Registro Nuevo Gerente");
		return "gerente/form";
	}
	
	@PostMapping(value="/save")
	public String save(Gerente gerente, Model model) {
		srvGerente.save(gerente);
		return "redirect:/gerente/list";
	}
	
	@GetMapping(value="/retrieve/{id}")
	public String retrieve(@PathVariable(value="id") Integer id, Model model) {
		Gerente gerente = srvGerente.findById(id);
		model.addAttribute("gerente", gerente);
		model.addAttribute("title", "Ver gerente");
		return "gerente/card";
	}
	
	@GetMapping(value="/update/{id}")
	public String update(@PathVariable(value="id") Integer id, Model model) {
		Gerente gerente = srvGerente.findById(id);
		model.addAttribute("gerente", gerente);
		model.addAttribute("title", "Actualizando el registro de " + gerente.toString());
		return "gerente/form";
	}
	
	@PostMapping(value="/delete/{id}")
	public String delete(@PathVariable(value="id") Integer id, Model model) {
		srvGerente.delete(id);
		return "redirect:/gerente/list";
	}
	
	@GetMapping(value="/list")
	public String list(Model model) {
		List<Gerente> gerentes = srvGerente.findAll();
		model.addAttribute("gerentes", gerentes);
		return "gerente/list";
	}
	
	@GetMapping(value="/{id}/servicios")
	public String services(@PathVariable(value="id") Integer id, Model model) {
		Gerente gerente = srvGerente.findById(id);
		model.addAttribute("gerente", gerente);
		
		List<Servicio> servicios = gerente.getServicios(); 
		model.addAttribute("servicios", servicios);
		
		return "gerente/services";
	}
}
