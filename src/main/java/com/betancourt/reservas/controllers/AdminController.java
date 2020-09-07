package com.betancourt.reservas.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.betancourt.reservas.entities.Gerente;
import com.betancourt.reservas.services.IGerenteService;

@Controller
@RequestMapping(value="/admin")
public class AdminController {
	
	@Autowired
	private IGerenteService srvGerente;
	
	@GetMapping(value="/panel")
	public String create(Model model) {
		List<Gerente> gerentes = srvGerente.findAll();
		model.addAttribute("gerentes", gerentes);
		return "admin/dashboard";
	}
}
