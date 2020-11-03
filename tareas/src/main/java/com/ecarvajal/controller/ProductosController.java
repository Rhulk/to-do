package com.ecarvajal.controller;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ecarvajal.model.Cliente;
import com.ecarvajal.model.Producto;
import com.ecarvajal.service.Listas;

@Controller
public class ProductosController {

	@Autowired
	Listas list = new Listas();
	
	public boolean cargaInicial=true;
	public boolean busqueda=false;
	
	List<Producto> productos = new LinkedList<Producto>();
	
	@GetMapping("/listproductos")
	public String list(Model vista) {
		System.out.println("Vista listado Productos");

		if (productos.isEmpty()  && cargaInicial) {
			System.out.println("Lista vacia de productos se inicializa ...");
			//Carga inicial de datos.
			productos = list.getProductos();
			cargaInicial=true;
		}
		
		if (busqueda) {
			busqueda=false;
			//vista.addAttribute("tareasEnEpera", listaEsperaB);
			//vista.addAttribute("tareasActivas", listaActivaB);
			
		}else {
			//vista.addAttribute("tareasEnEpera", listaEspera);
			//vista.addAttribute("tareasActivas", listaActiva);
		}		
		
		vista.addAttribute("productos", productos);
		
		return "productos/list";
	}
	
	@RequestMapping(value="/alta_producto")
	public String alta(@ModelAttribute("alta_producto") Producto producto) {
		System.out.println(" -- Alta Producto -- Controller --");
		producto.setId(proximoId());
		producto.setPublicado(true);
		productos.add(producto);
		return "redirect:/"+"listproductos";
	}
	
	@GetMapping("/stockIncremento/{id}")
	public String controlStockMas(@PathVariable int id ,Model vista) {

		for (int i=0; i< productos.size(); i++) {
			if (productos.get(i).getId()==id) {
				productos.get(i).setStock( productos.get(i).getStock()+1);
				System.out.println("-- Incremento de stock en: "+productos.get(i).toString());
			}
		}

		vista.addAttribute("productos", productos);
		
		return "redirect:/"+"listproductos";
	}
	
	@GetMapping("/stockDecremento/{id}")
	public String controlStockMenos(@PathVariable int id,Model vista) {
		
		for (int i=0; i< productos.size(); i++) {
			if (productos.get(i).getId()==id) {
				productos.get(i).setStock( productos.get(i).getStock()-1);
				System.out.println("-- Decrento de stock en: "+productos.get(i).toString());
			}
		}

		vista.addAttribute("productos", productos);
		
		return "redirect:/"+"listproductos";
	}
	
	@GetMapping("/web/{id}")
	public String publicWeb(@PathVariable int id,Model vista) {
		
		for (int i=0; i< productos.size(); i++) {
			if (productos.get(i).getId()==id) {
				if (productos.get(i).isPublicado()) {
					productos.get(i).setPublicado(false);
					System.out.println("-- Retirado de la Web: "+productos.get(i).toString());
					i=productos.size();
				}else {
					productos.get(i).setPublicado(true);
					System.out.println("-- Añadido a la Web: "+productos.get(i).toString());
					i=productos.size();
				}
			}
		}
		vista.addAttribute("productos", productos);
		return "redirect:/"+"listproductos";
	}
	
	public int proximoId() {
		int id =0;
		
		for(int i=0; i< productos.size() ;i++) {
			if (productos.get(i).getId() > id) id =productos.get(i).getId();
		}

		return id+=1;
	}
}
