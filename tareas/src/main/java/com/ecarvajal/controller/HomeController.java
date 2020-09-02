package com.ecarvajal.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;


import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ecarvajal.model.Tarea;




@Controller
public class HomeController {
	
	List<Tarea> listaActiva = new LinkedList<Tarea>();
	List<Tarea> listaActivaB = new LinkedList<Tarea>();
	List<Tarea> listaEspera = new LinkedList<Tarea>();
	List<Tarea> listaEsperaB = new LinkedList<Tarea>();
	boolean busqueda=false;
	
	Tarea tarea = new Tarea();
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
		System.out.println(" --- InitBinder ---");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor( dateFormat,false));
	}
	
	/*
	 * IMPORTANTE Aqui le estoy pasando el modelo a la vista para tener declarada la variable search en el form de la vista para la busqueda
	 * 
	 * 	
	 */
	@ModelAttribute
	public void setGenericos(Model model) {
		//String tarea= "Sin definir";
		Tarea tarea = new Tarea();
		Tarea tareaAlta = new Tarea();
		System.out.println("  IMPORTANTE  *******  Cargando datos a la vista --->> ");
		model.addAttribute("search", tarea);
		model.addAttribute("alta", tareaAlta);
		System.out.println(" -- setGenericos -- Se declaran variables para la vista");
	}

	@GetMapping("/")
	public String home() {
		return "home";
	}
	
	@GetMapping("index")
	public String inicio(Model vista) {
	
		if (listaEspera.isEmpty() && listaActiva.isEmpty()) {
			System.out.println("Lista vacia se inicializa ...");
			
			listaEspera = getLista("en espera");
			listaActiva = getLista("Activo");
		}
		
		if (busqueda) {
			busqueda=false;
			vista.addAttribute("tareasEnEpera", listaEsperaB);
			vista.addAttribute("tareasActivas", listaActivaB);
			
		}else {
			vista.addAttribute("tareasEnEpera", listaEspera);
			vista.addAttribute("tareasActivas", listaActiva);
		}
		return "index";
	}

	@RequestMapping(value="/search")
	public String buscar(@ModelAttribute("search") Tarea tarea, @RequestParam String action) {
		
		busqueda=true;

		if (action.equals("en espera"))	{  
			listaEsperaB = getLista("en espera", tarea.getTarea());
		}else {
			listaActivaB = getLista("Activo",tarea.getTarea());
		}
		
		return "redirect:/index";
	}
	
	
	@RequestMapping(value="/alta")
	public String alta(@ModelAttribute("alta") Tarea tarea, @RequestParam String action ) {
		tarea.setId(proximoId());
		if (action.equals("en espera"))	{  
			listaEspera.add(tarea);
		}else {
			listaActiva.add(tarea);
		}
		return "redirect:/index";
	}

	@GetMapping(value="/changeStatus/{id}")
	public String changeStatus(@PathVariable("id") int id, Model vista ) {
		//System.out.println(" -- ChangeStatus/"+id+" action "+action);
		System.out.println(" -- Lista espera -- "+listaEspera.toString());
		
		//if (action.equals("en espera"))	{ 
			for(int i=0; i< listaEspera.size() ;i++) {
				System.out.println(" -- i "+ i );
				if (listaEspera.get(i).getId() == id) {
					listaEspera.get(i).setStatus("Activo");
					listaActiva.add(listaEspera.get(i));
					listaEspera.remove(i);
					System.out.println(" - | lista en espera | - "+listaEspera.toString());
					System.out.println(" - | lista activo | - "+listaActiva.toString());
					return "redirect:/index";
				}
			}
			
	//	}else {
			for(int i=0; i< listaActiva.size() ;i++) {
				if (listaActiva.get(i).getId() == id) {
					listaActiva.get(i).setStatus("en espera");
					listaEspera.add( listaActiva.get(i));
					listaActiva.remove(i);
					System.out.println(" - | lista en espera | - "+listaEspera.toString());
					System.out.println(" - | lista activo | - "+listaActiva.toString());
				}
			}			
	//	}
		// recalcular las listas segun el estado tras el cambio de estado
		
		
	//	vista.addAttribute("tareasEnEpera", listaEspera);
		//lista.clear();
	//	vista.addAttribute("tareasActivas", listaActiva);

		
		return "redirect:/index";
	}

	
	/*
	 *  Lista temporal para simular datos de BBDD
	 * 
	 * Param: status: en espera y activo
	 * Param: tarea: tipo tarea por la que filtrar la lista simulada.
	 * 
	 * */
	
	private List<Tarea> getLista(String status, String tarea){
		System.out.println(" GetLista Status tarea "+ status+ " - " + tarea);
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
			tarea2.setStatus("Activo");
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
			
			System.out.println(" -- Status :"+ tarea1.getStatus() +" Status variable: "+status);
			System.out.println(" -- Tarea :"+ tarea1.getTarea() +" Tarea variable: "+tarea);
			
			if (status.equals(tarea1.getStatus() )) {
				if (tarea.equals(tarea1.getTarea() )) {
					lista.add(tarea1);
				}
			}
			if (status.equals(tarea2.getStatus())) if (tarea.equals(tarea2.getTarea())) lista.add(tarea2);
			if (status.equals(tarea3.getStatus())) if (tarea.equals(tarea3.getTarea())) lista.add(tarea3);

			
			System.out.println(" Busqueda por status y tarea: "+status+" "+tarea);
			System.out.println(" Tamaño lista: "+lista.size());
			return lista;
			
		}catch(ParseException e){
			System.out.println("Peto: "+e);
			return null;
		}catch(NullPointerException nu) {
			System.out.println(" Localizacion "+nu.getLocalizedMessage());
			System.out.println(" Mensaje : "+nu.getMessage());
			System.out.println(" Causa : "+nu.getCause());
			System.out.println(" Error : "+nu);
			return null;
		}
		
	}
	
	/*
	 *  Simula lista de tareas segun el status "en espera o activo"
	 * 
	 */
	
	private List<Tarea> getLista(String status){
		System.out.println(" GetLista Status");
		
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
			tarea2.setStatus("Activo");
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
	
	private List<Tarea> getLista(int id){
		System.out.println(" GetLista Status");
		
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
			tarea2.setStatus("Activo");
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
			

			
			if (id == tarea1.getId()) lista.add(tarea1);
			if (id == tarea2.getId()) lista.add(tarea2);
			if (id == tarea3.getId()) lista.add(tarea3);


			return lista;
			
		}catch(ParseException e){
			System.out.println("Peto: "+e);
			return null;
		}
	}
	public int proximoId() {
		int id =0;
		
		for(int i=0; i< listaActiva.size() ;i++) {
			if (listaActiva.get(i).getId() > id) id =listaActiva.get(i).getId();
		}
		for(int i=0; i< listaEspera.size() ;i++) {
			if (listaEspera.get(i).getId() > id) id =listaEspera.get(i).getId();
		}
		return id+=1;
	}
	
	
}
