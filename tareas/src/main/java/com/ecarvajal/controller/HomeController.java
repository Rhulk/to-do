package com.ecarvajal.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ecarvajal.model.Tarea;



@Controller
public class HomeController {

	@GetMapping("/")
	public String home() {
		return "home";
	}
	
	@GetMapping("index")
	public String inicio(Model vista) {
	
		vista.addAttribute("tareas", getLista());
		return "index";
	}
	/*
	 *  Lista temporal para simular datos de BBDD
	 * 
	 * 
	 * */
	
	private List<Tarea> getLista(){
		
		SimpleDateFormat formatear = new SimpleDateFormat("dd-mm-yyyy");
		List<Tarea> lista = null;
		
		try {
			lista = new LinkedList<Tarea>();
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
			
			lista.add(tarea1);
			lista.add(tarea2);

			
			return lista;
			
		}catch(ParseException e){
			System.out.println("Peto: "+e);
			return null;
		}
	}
	
}
