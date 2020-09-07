package com.betancourt.reservas.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.betancourt.reservas.entities.Cliente;
import com.betancourt.reservas.entities.Reservacion;
import com.betancourt.reservas.entities.Rol;
import com.betancourt.reservas.entities.Usuario;
import com.betancourt.reservas.services.IClienteService;
import com.betancourt.reservas.services.UsuarioService;


@Controller
@RequestMapping(value="/cliente")
public class ClienteController {
	
	@Autowired
	private IClienteService srvCliente;
	
	@Autowired
	private UsuarioService srvUsuario;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@GetMapping(value="/create")
	public String create(Model model) {
		Cliente cliente = new Cliente();
		model.addAttribute("cliente", cliente);
		model.addAttribute("title", "Registro Nuevo Cliente");
		return "cliente/form";
	}
	
	@PostMapping(value="/save")
	public String save(@Validated Cliente cliente, BindingResult result, Model model, SessionStatus status, RedirectAttributes flash) {
		try {
			
			String message = "Cliente agregado correctamente";
			String title = "Registro Nuevo Cliente";
			
			if(cliente.getIdCliente() != null) {
				message = "Cliente actualizado correctamente";
				title = "Actualizando el registro de " + cliente;
			}
			
			
			if(result.hasErrors()) {		
				model.addAttribute("title", title);			
				return "cliente/form";				
			}
			
			// Crear usuario
			Usuario usuario = new Usuario();
			String pass = cliente.getPassword();
			usuario.setGenero(cliente.getGenero());
			usuario.setEmail(cliente.getEmail());
			usuario.setNombres(cliente.getNombres());
			usuario.setTelefono(cliente.getTelefono());
			usuario.setApellidos(cliente.getApellidos());
			usuario.setCedula(cliente.getCedula());
			usuario.setNombre(cliente.getNombre());
			usuario.setPassword(encoder.encode(pass));			
			usuario.getRoles().add(new Rol("ROLE_USER"));
			usuario.setHabilitado(true);
			srvUsuario.save(usuario);
			// Fin crear usuario
			
			srvCliente.save(cliente);
			status.setComplete();
			flash.addFlashAttribute("success", message);
		} catch (Exception ex) {
			ex.printStackTrace();
			flash.addFlashAttribute("error", ex.getMessage());
			return "cliente/form";
		}
		return "redirect:/";
	}
	
	@GetMapping(value="/retrieve/{id}")
	public String retrieve(@PathVariable(value="id") Integer id, Model model) {
		Cliente cliente = srvCliente.findById(id);
		List<Reservacion> reservaciones = cliente.getReservaciones();
		model.addAttribute("cliente", cliente);
		model.addAttribute("reservaciones", reservaciones);
		model.addAttribute("title", "Ver cliente");
		return "cliente/card";
	}
	
	@GetMapping(value="/update/{id}")
	public String update(@PathVariable(value="id") Integer id, Model model) {
		Cliente cliente = srvCliente.findById(id);
		model.addAttribute("title", "Actualizar cliente");
		model.addAttribute("cliente", cliente);
		model.addAttribute("title", "Actualizando el registro de " + cliente.toString());
		return "cliente/form";
	}
	
	@PostMapping(value="/delete/{id}")
	public String delete(@PathVariable(value="id") Integer id, Model model) {
		srvCliente.delete(id);
		return "redirect:/cliente/list";
	}
	
	@GetMapping(value="/list")
	public String list(Model model) {
		List<Cliente> clientes = srvCliente.findAll();
		model.addAttribute("clientes", clientes);
		model.addAttribute("title", "Listado de clientes");
		return "cliente/list";
	}
}
