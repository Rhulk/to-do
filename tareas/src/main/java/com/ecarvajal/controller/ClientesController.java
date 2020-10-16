package com.ecarvajal.controller;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import com.ecarvajal.service.Listas;

@Controller
public class ClientesController {

	@Autowired
	Listas list = new Listas();
	
	List<Listas> clientes = new LinkedList<Listas>();
	
	@GetMapping("/listclientes")
	public String list(Model vista) {
		System.out.println("Vista listado clientes");
		 
		//vista.addAttribute("clientes", list.getClientes());// recuperamos los clientes seteados.
		
		return "clientes/list";
	}	

}
