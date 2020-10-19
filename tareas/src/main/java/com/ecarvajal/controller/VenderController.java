package com.ecarvajal.controller;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.ecarvajal.model.Producto;
import com.ecarvajal.service.Listas;

@Controller
public class VenderController {
	
	@Autowired
	Listas list = new Listas();
	
	List<Producto> productos = new LinkedList<Producto>();
	List<Producto> venta = new LinkedList<Producto>(); // nueva venta.
	
	@GetMapping("/vender")
	public String inicarVenta(Model vista) {
		System.out.println("-- Vista de la venta --");
		vista.addAttribute("carrito", venta);
		
		return "venta";
	}
	
	@PostMapping(value = "/agregar")
	public String agregarAlCarrito(@ModelAttribute Producto producto) {
		productos = list.getProductos(); // listado productos ficticio
		
		venta.add(producto);
		
		System.out.println("Add producto al carrito");
		return "/vender";
	}

}
