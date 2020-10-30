package com.ecarvajal.controller;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.ecarvajal.model.Cliente;
import com.ecarvajal.model.Mantenimiento;
import com.ecarvajal.service.EmailSenderService;
import com.ecarvajal.service.Listas;

@Controller
public class ClientesController {

	@Autowired
	Listas list = new Listas();
	EmailSenderService email = new EmailSenderService();
	
	public int id_detalle;
	
	List<Cliente> clientes = new LinkedList<Cliente>();
	List<Mantenimiento> mant = new LinkedList<Mantenimiento>();
	
	
	
	@GetMapping("/listclientes")
	public String list(Model vista) {
		System.out.println("Vista listado clientes");
		clientes = list.getClientes();
		vista.addAttribute("clientes", list.getClientes());// recuperamos los clientes seteados.
		
		return "clientes/list";
	}
	
	@GetMapping("/detallecliente/{id}")
	public String detalle(@PathVariable("id") int id) {
		System.out.println(" -- Detalle cliente vista id:"+ id);
		id_detalle=id;
		return "redirect:/"+"detallecliente";
	}
	
	@GetMapping("/detallecliente")
	public String detail(Model vista) {
		int id=id_detalle;
		// buscamos al cliente
		for (int i=0; i < clientes.size() ; i++ ) {
			if(clientes.get(i).getId() == id) {
				vista.addAttribute("cliente", clientes.get(i) );
				vista.addAttribute("mante", list.getMantenimientos(id_detalle));
				email.sendEmail(); // enviamos email
			}
		}
		return "clientes/detallecliente";
	}
	

}
