package com.betancourt.reservas.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.betancourt.reservas.dao.IUsuario;
import com.betancourt.reservas.entities.Cliente;
import com.betancourt.reservas.entities.Gerente;
import com.betancourt.reservas.entities.Rol;
import com.betancourt.reservas.entities.Usuario;
import com.betancourt.reservas.services.IClienteService;
import com.betancourt.reservas.services.IGerenteService;
import com.betancourt.reservas.services.UsuarioService;

@Controller
@RequestMapping(value="/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService service;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Autowired
	private UsuarioService srvUsuario;
	
	@Autowired
	private IUsuario daoUsuario;
	
	@Autowired
	private IClienteService srvCliente;
	
	@Autowired
	private IGerenteService srvGerente;
	
	@GetMapping(value="/create")
	public String registro(Model model) {	
		Usuario usuario = new Usuario();
		model.addAttribute("usuario", usuario);
		model.addAttribute("title", "ServiPase - Crear cuenta");				
		return "usuario/form";
	}
	
	@GetMapping(value="/cliente")
	public String registroCliente(Model model) {	
		Cliente cliente = new Cliente();
		model.addAttribute("cliente", cliente);
		model.addAttribute("title", "ServiPase - Crear cuenta");				
		return "cliente/form";
	}
	
	@GetMapping(value="/gerente")
	public String registroGerente(Model model) {	
		Gerente gerente = new Gerente();
		model.addAttribute("gerente", gerente);
		model.addAttribute("title", "ServiPase - Crear cuenta para ofrecer servicios");				
		return "gerente/form";
	}
	
	
	@GetMapping(value="/perfil")
	public String perfil(Model model, HttpServletRequest request, Authentication authentication) {	
		
		if(request.isUserInRole("USER")) {
			Usuario usuario = daoUsuario.findByNombre(authentication.getName());
			Cliente cliente = srvCliente.findByEmail(usuario.getEmail());
			return "redirect:/cliente/retrieve/" + cliente.getIdCliente();
		}
		
		if(request.isUserInRole("GERENTE")) {
			Usuario usuario = daoUsuario.findByNombre(authentication.getName());
			Gerente gerente = srvGerente.findByEmail(usuario.getEmail());
			return "redirect:/gerente/retrieve/" + gerente.getIdGerente();
		}
		
		return "redirect:/admin/panel";
	}
	
	
	@PostMapping(value="/save")
	public String save(@Validated Usuario usuario, BindingResult result, Model model,
			RedirectAttributes flash) {
		try {
			if(result.hasErrors())
			{	
				model.addAttribute("title", "Registro de nuevo usuario");
				model.addAttribute("usuario", usuario);
				System.out.println("Error");
				System.out.println(result.getAllErrors().get(0));
				return "usuario/form";
			}			
			String pass = usuario.getPassword();
			usuario.setPassword(encoder.encode(pass));			
			usuario.getRoles().add(new Rol("ROLE_ADMIN"));
			usuario.setHabilitado(true);
			service.save(usuario);
			flash.addFlashAttribute("success", "El usuario fue agregado con éxito.");
		}
		catch(Exception ex) {
			ex.printStackTrace();
			flash.addFlashAttribute("error", "El usuario no pudo ser agregado.");
		}
		return "redirect:/login";		
	} 
}
