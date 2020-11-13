package com.ecarvajal.controller;

import java.io.File;

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

import com.ecarvajal.model.Cliente;
import com.ecarvajal.model.Producto;
import com.ecarvajal.model.ProductoParaVender;

import com.ecarvajal.service.Listas;
import com.ecarvajal.ticketExcel.Ticket;
import com.ecarvajal.xdoc.ODTProjectWithVelocity;

import com.ecarvajal.xdoc.samples.freemarker.ODTNativeLineBreakAndTabWithFreemarker;



@Controller
public class VenderController {
	
	@Autowired
	Listas list = new Listas();
	
	
	
	
	ODTProjectWithVelocity xxx = new ODTProjectWithVelocity();
	ODTNativeLineBreakAndTabWithFreemarker simple = new ODTNativeLineBreakAndTabWithFreemarker();
	
	public float total=0;
	public int postProductoTicke=13;
	public boolean nueva=true;
	public boolean clienteSelect=false;
	public int codCompra=1; // proximamente se recuperar de la tabla ventas.
	
	List<Producto> productos = new LinkedList<Producto>();
	List<ProductoParaVender> venta = new LinkedList<ProductoParaVender>(); // nueva venta.
	List<Cliente> cliente = new LinkedList<Cliente>();
	
	
	Ticket ticket = new Ticket();
	
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
		boolean add=true;
		// buscarlo en la lista de venta
		
		//buscamos el produccto en BBDD :)
		for(int i=0; i< productos.size(); i++) {
			if (producto.getCodProducto().equals(productos.get(i).getCodProducto())) { // econtrado
				// buscamos el producto en la lista de venta.
				for (int x=0; x < venta.size(); x++) {
					if(producto.getCodProducto().equals(venta.get(x).getCodProducto())) {// aumetamos la cantidad
						venta.get(x).setCantidad(venta.get(x).getCantidad()+1);
						total = (float) (total + (	(productos.get(i).getPvp() - (productos.get(i).getDescuento() * productos.get(i).getPvp()) / 100)	))	; // añadimos el precio del articulo añadido.
						venta.get(x).setTotal(venta.get(x).calTotal());

						x=venta.size();
						i=productos.size();
						add=false;
					}
				}
				if(add) {
					addProducto.setCantidad(1);
					addProducto.setNombre(productos.get(i).getNombre());
					addProducto.setCodProducto(productos.get(i).getCodProducto());
					addProducto.setPvp(productos.get(i).getPvp());
					addProducto.setDescuento(productos.get(i).getDescuento());
					addProducto.setTotal(addProducto.calTotal());
					venta.add(addProducto);
					total += addProducto.getTotal(); // total de la venta	
					ticket.generar(productos.get(i).getCodProducto(), 
							productos.get(i).getNombre(), "1", productos.get(i).getDescuento(),
							productos.get(i).getPrecio(), postProductoTicke,nueva);
					postProductoTicke++;
					nueva = false;
					
				}

				


				
				System.out.println("Add producto al carrito: "+producto.getCodProducto());
			}
		}
		//Recuperamos datos del producto a vender
		
		//venta.add(producto);
		
		
		return "redirect:/"+"vender";
	}
	
	@RequestMapping(value="/tramitarVenta")
	public String finalizarVenta( @RequestParam String action) {
		postProductoTicke=13;
		nueva=true;
		
		

		if (action.equals("tramitar"))	{  
			System.out.println(" --- Tramitada la venta.");
			//xxx.xdox();
			venta.clear();
			total=0;
			//simple.main(null);
			ticket.tramita(""+codCompra);
			codCompra++;
			ticket.limpiar();
		}else {
			System.out.println(" --- Se limpia la caja...");
			venta.clear();
			total=0;
	          File outFile = new File("ODTProjectWithVelocity_Outh.odt");
	          System.out.println("-- Ruta del fichero: |"+outFile.getAbsolutePath()+"|");
	          System.out.println(" --- Se limpia la caja...");
	          ticket.limpiar();
		}
		
		return "redirect:/"+"vender";
	}


}
