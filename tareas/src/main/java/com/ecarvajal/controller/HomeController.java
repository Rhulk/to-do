package com.ecarvajal.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ecarvajal.model.Tarea;





@Controller
public class HomeController {
	
	List<Tarea> listaActiva = new LinkedList<Tarea>();
	List<Tarea> listaEspera = new LinkedList<Tarea>();

	@GetMapping("/")
	public String home() {
		return "home";
	}
	
	@GetMapping("index")
	public String inicio(Model vista) {
	
		if (listaEspera.isEmpty()) {
			System.out.println("Lista vacia");
			
		}
		
			
		vista.addAttribute("tareasEnEpera", getLista("en espera"));
		//lista.clear();
		vista.addAttribute("tareasActivas", getLista("activa"));
		return "index";
	}
	
	@RequestMapping( "search")
	public String buscar(@RequestParam("tarea") String tarea, @RequestParam("tBusqueda") String tBusqueda, Model vista) {

		if (tBusqueda=="en espera")	{
			listaEspera = getLista("en espera",tarea);
		}else {
			listaActiva = getLista("activa",tarea);
		}
		
		return "index";
	}
	/*
	 *  Lista temporal para simular datos de BBDD
	 * 
	 * 
	 * */
	
	private List<Tarea> getLista(String status, String tarea){
		
		SimpleDateFormat formatear = new SimpleDateFormat("dd-mm-yyyy");
		List<Tarea> lista = new LinkedList<Tarea>();
		
		try {
			
			Tarea tarea1 = new Tarea();
			tarea1.setId(1);
			tarea1.setTarea("Soporte");
			tarea1.setPrioridad("Alta");
			tarea1.setCliente("Lorca");
			tarea1.setfAlta(formatear.parse("31-07-2020"));
			tarea1.setfAlert(formatear.parse("23-08-2020"));
			tarea1.setStatus("en espera");
			tarea1.setDescripcion("Cambio de servidor");
			
			Tarea tarea2 = new Tarea();
			tarea2.setId(2);
			tarea2.setTarea("Desarrollo");
			tarea2.setPrioridad("Alta");
			tarea2.setCliente("kike");
			tarea2.setfAlta(formatear.parse("31-07-2020"));
			tarea2.setfAlert(formatear.parse("23-08-2020"));
			tarea2.setStatus("activa");
			tarea2.setDescripcion("Crear app web to-do");
			
			
			Tarea tarea3 = new Tarea();
			tarea3.setId(3);
			tarea3.setTarea("Desarrollo");
			tarea3.setPrioridad("Alta");
			tarea3.setCliente("kike");
			tarea3.setfAlta(formatear.parse("31-07-2020"));
			tarea3.setfAlert(formatear.parse("23-08-2020"));
			tarea3.setStatus("en espera");
			tarea3.setDescripcion("Crear app web to-do");
			
			if (status.equals(tarea1.getStatus())) if (tarea.equals(tarea1.getTarea())) lista.add(tarea1);
			if (status.equals(tarea2.getStatus())) if (tarea.equals(tarea2.getTarea())) lista.add(tarea2);
			if (status.equals(tarea3.getStatus())) if (tarea.equals(tarea3.getTarea())) lista.add(tarea3);

			
			System.out.println(" Busqueda por status y tarea: "+status+" "+tarea);
			System.out.println(" Tamaño lista: "+lista.size());
			return lista;
			
		}catch(ParseException e){
			System.out.println("Peto: "+e);
			return null;
		}
	}
	
	private List<Tarea> getLista(String status){
		
		SimpleDateFormat formatear = new SimpleDateFormat("dd-mm-yyyy");
		List<Tarea> lista = new LinkedList<Tarea>();
		
		try {
			
			Tarea tarea1 = new Tarea();
			tarea1.setId(1);
			tarea1.setTarea("Soporte");
			tarea1.setPrioridad("Alta");
			tarea1.setCliente("Lorca");
			tarea1.setfAlta(formatear.parse("31-07-2020"));
			tarea1.setfAlert(formatear.parse("23-08-2020"));
			tarea1.setStatus("en espera");
			tarea1.setDescripcion("Cambio de servidor");
			
			Tarea tarea2 = new Tarea();
			tarea2.setId(2);
			tarea2.setTarea("Desarrollo");
			tarea2.setPrioridad("Alta");
			tarea2.setCliente("kike");
			tarea2.setfAlta(formatear.parse("31-07-2020"));
			tarea2.setfAlert(formatear.parse("23-08-2020"));
			tarea2.setStatus("activa");
			tarea2.setDescripcion("Crear app web to-do");
			
			
			Tarea tarea3 = new Tarea();
			tarea3.setId(3);
			tarea3.setTarea("Desarrollo");
			tarea3.setPrioridad("Alta");
			tarea3.setCliente("kike");
			tarea3.setfAlta(formatear.parse("31-07-2020"));
			tarea3.setfAlert(formatear.parse("23-08-2020"));
			tarea3.setStatus("en espera");
			tarea3.setDescripcion("Crear app web to-do");
			

			
			if (status.equals(tarea1.getStatus())) lista.add(tarea1);
			if (status.equals(tarea2.getStatus())) lista.add(tarea2);
			if (status.equals(tarea3.getStatus())) lista.add(tarea3);

			System.out.println(" Busqueda por status: "+status);
			System.out.println(" Tamaño lista: "+lista.size());
			return lista;
			
		}catch(ParseException e){
			System.out.println("Peto: "+e);
			return null;
		}
	}
	
}
