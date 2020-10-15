package com.ecarvajal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ClientesController {

	
	@GetMapping("/listclientes")
	public String list() {
		System.out.println("Vista listado clientes");
		
		return "clientes/list";
	}
}
