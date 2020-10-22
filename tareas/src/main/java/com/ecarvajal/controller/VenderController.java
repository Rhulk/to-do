package com.ecarvajal.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
import com.ecarvajal.xdoc.ODTProjectWithVelocity;
import com.ecarvajal.xdoc.Project;

import fr.opensagres.xdocreport.core.XDocReportException;
import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.TemplateEngineKind;

@Controller
public class VenderController {
	
	@Autowired
	Listas list = new Listas();
	
	
	ODTProjectWithVelocity xxx = new ODTProjectWithVelocity();
	
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
				addProducto.setTotal(addProducto.calTotal());
				venta.add(addProducto);
				total += addProducto.getTotal();
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
			xxx.xdox();
			venta.clear();
			total=0;
		}else {
			System.out.println(" --- Se limpia la caja...");
			venta.clear();
			total=0;
			generarODT();
	          File outFile = new File("ODTProjectWithVelocity_Outh.odt");
	          System.out.println("-- Ruta del fichero: |"+outFile.getAbsolutePath()+"|");
	          System.out.println(" --- Se limpia la caja...");
		}
		
		return "redirect:/"+"vender";
	}
	
	public void generarODT() {
	    try {
	        System.out.println("Generamos reporte");
	          // 1) Load ODT file by filling Velocity template engine and cache it to the registry
	          InputStream in = ODTProjectWithVelocity.class.getResourceAsStream("ticket.odt");
	          IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in,TemplateEngineKind.Velocity);
	          System.out.println(" --- Tramitada la venta.");
	          // 2) Create context Java model
	          IContext context = report.createContext();
	          Project project = new Project("XDocReport");
	          context.put("project", project);

	          // 3) Generate report by merging Java model with the ODT
	          File outFile = new File("ODTProjectWithVelocity_Outh.odt");
	          System.out.println("-- Ruta del fichero: |"+outFile.getAbsolutePath()+"|");
	          OutputStream out = new FileOutputStream(outFile);
	          report.process(context, out);
	          System.out.println("Creado ticket");
	        } catch (IOException e) {
	          e.printStackTrace();
	        } catch (XDocReportException e) {
	          e.printStackTrace();
	        }
	      }
	
	


}
