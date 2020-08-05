package com.betancourt.reservas.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.betancourt.reservas.entities.Gerente;
import com.betancourt.reservas.services.IGerenteService;

@Controller
@RequestMapping(value="/")
public class InicioController {
	
	@Autowired
	private IGerenteService srvGerente;
	
	@GetMapping(value="/")
	public String create(Model model) {
		List<Gerente> gerentes = srvGerente.findAll();
		model.addAttribute("gerentes", gerentes);
		return "inicio";
	}
	

	@GetMapping(value="/login")
	public String login(@RequestParam(value="error", required=false) String error, 
			Model model, Principal principal, RedirectAttributes flash) {
		
		if(principal != null) {
			flash.addFlashAttribute("info", "El usuario ya tiene una sesión activa.");
			return "redirect:/";
		}		
		if(error != null) {
			model.addAttribute("error", "Usuario o contraseña incorrectas");
		}				
		return "acceso";
	}

}
