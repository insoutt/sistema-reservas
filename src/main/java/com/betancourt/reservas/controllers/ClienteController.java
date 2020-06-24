package com.betancourt.reservas.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.betancourt.reservas.entities.Cliente;
import com.betancourt.reservas.services.IClienteService;


@Controller
@RequestMapping(value="/cliente")
public class ClienteController {
	
	@Autowired
	private IClienteService srvCliente;
	
	@GetMapping(value="/create")
	public String create(Model model) {
		Cliente cliente = new Cliente();
		model.addAttribute("cliente", cliente);
		model.addAttribute("title", "Registro Nuevo Cliente");
		return "cliente/form";
	}
	
	@PostMapping(value="/save")
	public String save(Cliente cliente, Model model) {
		srvCliente.save(cliente);
		return "redirect:/cliente/list";
	}
	
	@GetMapping(value="/retrieve/{id}")
	public String retrieve(@PathVariable(value="id") Integer id, Model model) {
		Cliente cliente = srvCliente.findById(id);
		model.addAttribute("cliente", cliente);
		model.addAttribute("cliente", "Ver servicio");
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
	
	@GetMapping(value="/delete/{id}")
	public String delete(@PathVariable(value="id") Integer id, Model model) {
		srvCliente.delete(id);
		return "redirect:/cliente/list";
	}
	
	@GetMapping(value="/list")
	public String list(Model model) {
		List<Cliente> clientes = srvCliente.findAll();
		model.addAttribute("clientes", clientes);
		return "cliente/list";
	}
}
