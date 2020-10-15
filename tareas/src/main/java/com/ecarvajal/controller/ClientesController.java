package com.ecarvajal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ClientesController {

	@GetMapping("/clientes/listado")
	public String listado() {
		System.out.println("Vista listado clientes");
		
		return "clientes/listadoclientes";
	}
}
