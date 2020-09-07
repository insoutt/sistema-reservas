package com.betancourt.reservas.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	public String save(@Validated Gerente gerente, BindingResult result, Model model,
			@RequestParam("photo") MultipartFile image,
			SessionStatus status, RedirectAttributes flash) {
		
		try {
			
			String message = "Gerente agregado correctamente";
			String title = "Registro Nuevo Gerente";
			
			if(gerente.getIdGerente() != null) {
				message = "Gerente actualizado correctamente";
				title = "Actualizando el registro de " + gerente;
			}
			
			if(result.hasErrors()) {
				model.addAttribute("title", title);							
				return "gerente/form";				
			}
			
			if (!image.isEmpty()) {				
				Path dir = Paths.get("src//main//resources//static//photos");
				String rootPath = dir.toFile().getAbsolutePath();
				try {
					byte[] bytes = image.getBytes();
					Path rutaCompleta = Paths.get(rootPath + "//" + image.getOriginalFilename());
					Files.write(rutaCompleta, bytes);
					gerente.setImagen(image.getOriginalFilename());

				} catch (IOException e) {
					e.printStackTrace();
				}
			}		
			
			srvGerente.save(gerente);
			status.setComplete();
			flash.addFlashAttribute("success", message);
		} catch (Exception ex) {
			flash.addFlashAttribute("error", ex.getMessage());
		}
		return "redirect:/gerente/list";
	}
	
	@GetMapping(value="/retrieve/{id}")
	public String retrieve(@PathVariable(value="id") Integer id, Model model) {
		Servicio servicio = new Servicio();
		model.addAttribute("servicio", servicio);
		
		Gerente gerente = srvGerente.findById(id);
		model.addAttribute("gerente", gerente);
		
		List<Servicio> servicios = gerente.getServicios(); 
		model.addAttribute("servicios", servicios);
		
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
		model.addAttribute("title", "Listado de gerentes");
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
