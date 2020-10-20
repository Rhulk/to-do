package com.ecarvajal.controller;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ecarvajal.model.Producto;
import com.ecarvajal.model.ProductoParaVender;
import com.ecarvajal.model.Tarea;
import com.ecarvajal.service.Listas;

@Controller
public class VenderController {
	
	@Autowired
	Listas list = new Listas();
	
	public float total=0;
	
	List<Producto> productos = new LinkedList<Producto>();
	List<ProductoParaVender> venta = new LinkedList<ProductoParaVender>(); // nueva venta.
	
	@GetMapping("/vender")
	public String inicarVenta(Model vista) {
		System.out.println("-- Vista de la venta --");
		vista.addAttribute("producto", new Producto());
		vista.addAttribute("carrito", venta);
		
		System.out.println("-- Listado Venta -- Size :"+venta.size());
		for(int i=0;i<venta.size();i++) {
			System.out.println(" >>> Objeto añadido: "+venta.get(i).toString());
			
		}
		vista.addAttribute("total", total);
		return "vender/venta";
	}
	
	@PostMapping(value = "/agregar")
	public String agregarAlCarrito(@ModelAttribute Producto producto) {
		productos = list.getProductos(); // listado productos ficticio
		ProductoParaVender addProducto = new ProductoParaVender();
		// buscarlo en la lista de venta
		
		//buscamos el produccto en BBDD :)
		for(int i=0; i< productos.size(); i++) {
			if (producto.getCodProducto().equals(productos.get(i).getCodProducto())) { // econtrado
				addProducto.setCantidad(1);
				addProducto.setNombre(productos.get(i).getNombre());
				addProducto.setCodProducto(productos.get(i).getCodProducto());
				addProducto.setPvp(productos.get(i).getPvp());
				addProducto.setDescuento(productos.get(i).getDescuento());
				addProducto.setTotal(addProducto.getTotal());
				venta.add(addProducto);
				total += venta.get(i).getTotal();
				System.out.println("Add producto al carrito: "+producto.getCodProducto());
			}
		}
		//Recuperamos datos del producto a vender
		
		//venta.add(producto);
		
		
		return "redirect:/"+"vender";
	}
	
	@RequestMapping(value="/tramitarVenta")
	public String buscar( @RequestParam String action) {
		
		

		if (action.equals("tramitar"))	{  
			System.out.println(" --- Tramitada la venta.");
			venta.clear();
			total=0;
		}else {
			System.out.println(" --- Se limpia la caja...");
			venta.clear();
			total=0;
		}
		
		return "redirect:/"+"vender";
	}
	


}
