package com.ecarvajal.controller;

import java.io.File;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	
	public boolean pausaVenta=false;
	
	public float total=0;
	public float pausa_total=0;
	public int postProductoTicke=13;
	public int pausa_postProductoTicke=13;
	public boolean nueva=true;
	public boolean pausa_nueva=true;
	public boolean clienteSelect=false;
	public boolean pausa_clienteSelect=false;
	public int codCompra=1; // proximamente se recuperar de la tabla ventas.
	
	List<Producto> productos = new LinkedList<Producto>();
	List<ProductoParaVender> venta = new LinkedList<ProductoParaVender>(); // nueva venta.
	List<ProductoParaVender> pausa_venta = new LinkedList<ProductoParaVender>(); // venta en espera
	List<Cliente> clientes = new LinkedList<Cliente>();
	
	Cliente client = new Cliente();
	Cliente pausa_client = new Cliente();
	
	String idcliente;
	String pausa_idcliente;
	
	Ticket ticket = new Ticket();
	Ticket pausa_ticket = new Ticket();
	
	@GetMapping("/vender")
	public String inicarVenta(Model vista) {
		System.out.println("-- Vista de la venta --");
		clientes = list.getClientes();
		if (!clienteSelect) {
			System.out.println(" -- Sin cliente Select --");
			vista.addAttribute("clientes", clientes);
			vista.addAttribute("cliente", new Cliente());
		}else {
			vista.addAttribute("cliente", client);
		}
		vista.addAttribute("producto", new Producto());
		
		System.out.println("-- Listado Venta -- Size :"+venta.size());
		for(int i=0;i<venta.size();i++) {
			venta.get(i).setId(i);
			System.out.println(" -- Stock -- "+venta.get(i).getStock());
			System.out.println(" >>> Objeto añadido: "+venta.get(i).toString());	
		}
		
		vista.addAttribute("carrito", venta);
		vista.addAttribute("total", total);
		return "vender/venta";
	}
	
	@PostMapping(value ="/agregarCliente")
	public String agregarCliente(@ModelAttribute Cliente cliente) {
		System.out.println(" -- Añadido Cliente --"+cliente.toString());// recupero el telenfono valor unico
		
		for( int index=0; index< clientes.size();index++) {
			if ( clientes.get(index).telefono == Integer.parseInt(cliente.getNombre()) ){
				client = clientes.get(index);
				clienteSelect= true;
				index=clientes.size();
			}
		}
		
		return "redirect:/"+"vender";
	}
	@PostMapping("/limpiarCliente")
	public String limpiarClietne() {
		client = new Cliente();
		clienteSelect= false;
		return "redirect:/"+"vender";
	}
	
	@PostMapping("/limpiarNoti")
	public String limpiarNoti() {
		return "redirect:/"+"vender";
	}
	
	@PostMapping(value = "/agregar")
	public String agregarAlCarrito(@ModelAttribute Producto producto, BindingResult result, RedirectAttributes redirectAttrs) {
		System.out.println(" -- Producto --"+producto.toString());
		if(result.hasErrors()) {		
			System.out.println(" -- Hay errores de validación --");
		}
		productos = list.getProductos(); // listado productos ficticio
		ProductoParaVender addProducto = new ProductoParaVender();
		boolean add=true;
		boolean encontrado=false;
		// buscarlo en la lista de venta
		
		//buscamos el produccto en BBDD :)
		for(int i=0; i< productos.size(); i++) {
			if (producto.getCodProducto().equals(productos.get(i).getCodProducto())) { // encontrado
				encontrado = true;
				// buscamos el producto en la lista de venta.
				for (int x=0; x < venta.size(); x++) {
					if(producto.getCodProducto().equals(venta.get(x).getCodProducto()) ) {// aumetamos la cantidad
						if (venta.get(x).getStock() > 0) { // sobre la venta.
							venta.get(x).setCantidad(venta.get(x).getCantidad()+1);
							venta.get(x).setStock(venta.get(x).getStock()-1); // validar el stock
							total = (float) (total + (	(productos.get(i).getPvp() - (productos.get(i).getDescuento() * productos.get(i).getPvp()) / 100)	))	; // añadimos el precio del articulo añadido.
							venta.get(x).setTotal(venta.get(x).calTotal());
	
							x=venta.size();
							i=productos.size();
							add=false;
						}else {
							x=venta.size();
							i=productos.size();
							add=false;
							
							redirectAttrs
							.addFlashAttribute("mensaje", "No hay stock suficiente.")
						    .addFlashAttribute("clase", "warning");
							return "redirect:/"+"vender";						
						}
					}
				}
				if(add) {

					if (productos.get(i).getStock() > 0) {
						addProducto.setCantidad(1);
						addProducto.setNombre(productos.get(i).getNombre());
						addProducto.setCodProducto(productos.get(i).getCodProducto());
						addProducto.setPvp(productos.get(i).getPvp());
						addProducto.setDescuento(productos.get(i).getDescuento());
						addProducto.setTotal(addProducto.calTotal());
						addProducto.setStock(productos.get(i).getStock()-1);
						venta.add(addProducto);
						total += addProducto.getTotal(); // total de la venta	
						ticket.generar(productos.get(i).getCodProducto(), 
								productos.get(i).getNombre(), "1", productos.get(i).getDescuento(),
								productos.get(i).getPrecio(), postProductoTicke,nueva);
						postProductoTicke++;
						nueva = false;
					}else {
						redirectAttrs
						.addFlashAttribute("mensaje", "No hay stock suficiente.")
					    .addFlashAttribute("clase", "warning");
						return "redirect:/"+"vender";	
					}
					
				}

				System.out.println("Add producto al carrito: "+producto.getCodProducto());
				return "redirect:/"+"vender";
			}
		}
		
		if (producto.codProducto.indexOf("+") != -1) {
			System.out.println(" Añadimos el objeto manualmente ");
			System.out.println(" -- posición blanco "+producto.codProducto.indexOf(" "));
			int postBlanc;
			
			if (producto.codProducto.indexOf(" ") == -1) {
				postBlanc = producto.codProducto.length();
			}else {
				postBlanc = producto.codProducto.indexOf(" ");
			}
			addProducto.setCantidad(1);
			addProducto.setNombre(producto.codProducto.substring(postBlanc));
			addProducto.setCodProducto("Manual");
			addProducto.setPvp(Integer.parseInt( producto.codProducto.substring(producto.codProducto.indexOf("+"), postBlanc ) ));
			addProducto.setDescuento(0);
			addProducto.setTotal(addProducto.calTotal());
			venta.add(addProducto);
			total += addProducto.getTotal(); // total de la venta	
			ticket.generar(addProducto.codProducto, 
					addProducto.nombre, "1", addProducto.descuento,
					0, postProductoTicke,nueva);
			postProductoTicke++;
			nueva = false;
			encontrado = true;
			
			// notificaciones
			redirectAttrs
			.addFlashAttribute("mensaje", "Añadido producto o servicio manualmente").addFlashAttribute("clase", "warning");
			return "redirect:/"+"vender";		
		}
		
		if(!encontrado ) {
			redirectAttrs
			.addFlashAttribute("mensaje", "No existe ese producto en el inventario")
		    .addFlashAttribute("clase", "warning");
			return "redirect:/"+"vender";
		}
		
		
		return "redirect:/"+"vender";
	}
	
	@RequestMapping(value="/pausarVenta")
	public String pausarVenta() {
		System.out.println("-- Pausada Venta --");
		pausaVenta = true;
		
		
		
		return "redirect:/"+"vender";
	}
	
	@RequestMapping(value="/tramitarVenta")
	public String finalizarVenta( @RequestParam String action) {
		postProductoTicke=13;
		nueva=true;
		client = new Cliente();
		clienteSelect= false;		

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
	
	@GetMapping("/quitarPoducto/{id}")
	String quitarProducto(@PathVariable("id") int id) {
		System.out.println(" -- Eliminado producto de la venta --"+ venta.get(id).calTotal());
		float descontar = (float) venta.get(id).calTotal();
		System.out.println(" A descontar ... "+descontar);
		System.out.println(" Total: "+total);
		total = (float) (total - descontar);
		System.out.println(" Total = "+total);
		venta.remove(id);
		return "redirect:/"+"vender";		
	}

	@GetMapping("/disminuir/{id}")
	String disminuir(@PathVariable("id") int id) {
    	float porcentaje = (float) ((venta.get(id).descuento * venta.get(id).pvp) / 100);
    	float pvp =(float) venta.get(id).pvp;
    	float descontar =pvp - porcentaje;
    	total = (float) (total - (descontar)) ;
    	venta.get(id).cantidad -= 1;
    	venta.get(id).stock += 1;
    	venta.get(id).calTotal();
    	if (venta.get(id).cantidad == 0) {
    		venta.remove(id);
    	}
    	
		return "redirect:/"+"vender";
	}
	
	@GetMapping("/aumentar/{id}")
	String aumentar(@PathVariable("id") int id, RedirectAttributes redirectAttrs) {
		for (int x=0; x < venta.size(); x++) {
	    	if (venta.get(x).id == id) {
	    		if (venta.get(x).getStock()> 0 || venta.get(x).codProducto.equalsIgnoreCase("Manual")) {
	    			if (!venta.get(x).codProducto.equalsIgnoreCase("Manual")) {
	    				venta.get(x).stock -=1;
	    			}
			    	double porcentaje = (venta.get(id).descuento * venta.get(id).pvp) / 100;
			    	float pvp =(float) venta.get(id).pvp;
			    	total = (float) (total + (pvp - porcentaje)) ;
			    	venta.get(id).cantidad += 1;
	    		}else {
					redirectAttrs
					.addFlashAttribute("mensaje", "No hay stock suficiente.")
				    .addFlashAttribute("clase", "warning");
					return "redirect:/"+"vender";	    			
	    		}
	    	}

    	}
    	venta.get(id).calTotal();
		return "redirect:/"+"vender";
	}
}
