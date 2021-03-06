package com.ecarvajal.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.poi.EmptyFileException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

import com.ecarvajal.model.Registro;
import com.ecarvajal.model.Tarea;
import com.ecarvajal.service.ClienteService;
//import com.ecarvajal.model.Task;
import com.ecarvajal.service.CrearFicherosExcel;
import com.ecarvajal.service.HomeService;
import com.ecarvajal.service.Intensidad;
import com.ecarvajal.service.Listas;
import com.ecarvajal.service.TareaService;

@Controller
public class HomeController {
	SimpleDateFormat formatear = new SimpleDateFormat("dd-mm-yyyy");
	
	@Autowired
	private TareaService tareaService = new TareaService();
	
	@Autowired
	private ClienteService clienteService;
		
	List<Tarea> listaActiva = new LinkedList<Tarea>();
	List<Tarea> listaActivaB = new LinkedList<Tarea>();
	List<Tarea> listaEspera = new LinkedList<Tarea>();
	List<Tarea> listaEsperaB = new LinkedList<Tarea>();
	
	
	
	List<Registro> registros = new LinkedList<Registro>();
//	List<Registro> rHistorico = new LinkedList<Registro>();
	
	boolean carga_inicial=true;
	boolean busqueda=false;
	int id_registro=0;
	int id_=0;
	
	String ruta="src/main/resources/static/doc/Registro.xlsx";
	String vistaIndex="orange";

	@Autowired
	Listas list = new Listas();	
	
	@Autowired
	HomeService hService = new HomeService();
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
		//System.out.println(" --- InitBinder ---");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
        //SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
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

	//	System.out.println("  IMPORTANTE  *******  Cargando datos a la vista --->> ");
		model.addAttribute("search",new Tarea());
		model.addAttribute("alta", new Tarea());
		model.addAttribute("edit", new Tarea());
		model.addAttribute("view", new Tarea());
		model.addAttribute("registro", new Registro());
		model.addAttribute("clientes", clienteService.buscarTodos());
	//	model.addAttribute("reqTask", new Task());
	//	System.out.println(" -- setGenericos -- Se declaran variables para la vista");
	}
	
	


	@GetMapping("/")
	public String home(Model vista) {
		vistaIndex="home";
		if (listaEspera.isEmpty() && listaActiva.isEmpty() && carga_inicial) {
			System.out.println("Lista vacia se inicializa ...");
			//Carga inicial de datos.
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
		return "home";
	}

	@GetMapping("/detalle/{id}")
	public String detail(@PathVariable("id") int id, Model vista) {
		id_=id;

		return "redirect:/"+"detalle";
	}
	@GetMapping("/detalle")
	public String detalle(Model vista) {
		int id=id_;
		for(int i=0; i< listaEspera.size() ;i++) {
			if (listaEspera.get(i).getId() == id) {
				vista.addAttribute("hReg", getRegistrosByTarea(id));
				vista.addAttribute("view", listaEspera.get(i));
				return "detalle";
			}
		}
		for(int i=0; i< listaActiva.size() ;i++) {
			if (listaActiva.get(i).getId() == id) {
				vista.addAttribute("hReg", getRegistrosByTarea(id));
				vista.addAttribute("view", listaActiva.get(i));
				return "detalle";
			}
		}
		return "detalle";
	}	
	
	@GetMapping("/orange/entrenoTiempoIntensidad")
	public String entrenoTiempoIntensidad() {
		vistaIndex="orange";
		Intensidad entreno = new Intensidad();
		entreno.start(150);
		
		return "redirect:/"+vistaIndex;
	}
	
	@GetMapping("/orange")
	public String orange(Model vista) {
		vistaIndex="orange";
		if (listaEspera.isEmpty() && listaActiva.isEmpty() && carga_inicial) {
			System.out.println("Lista vacia se inicializa ...");
			//Carga inicial de datos.
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
		

		return vistaIndex;
	}
	
	@GetMapping("/orange2")
	public String orangeb(Model vista) {
		vistaIndex="orange2";
		if (listaEspera.isEmpty() && listaActiva.isEmpty() && carga_inicial) {
			System.out.println("Lista vacia se inicializa ...");
			//Carga inicial de datos.
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
		

		return vistaIndex;
	}
	
	@GetMapping("index")
	public String inicio(Model vista) {
		vistaIndex="index";
		//hService.prueba(); // para escribir en un ods
		
		if (listaEspera.isEmpty() && listaActiva.isEmpty() && carga_inicial) {
			System.out.println("Lista vacia se inicializa ...");
			//Carga inicial de datos.
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
		return vistaIndex;
	}
/*
 * 
 * Temporal para el menu de opciones
 * 
 * 
 */
	@GetMapping("index2")
	public String inicioB(Model vista) {
		vistaIndex="index2";
		//hService.prueba(); // para escribir en un ods
		
		if (listaEspera.isEmpty() && listaActiva.isEmpty() && carga_inicial) {
			System.out.println("Lista vacia se inicializa ...");
			//Carga inicial de datos.
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
		return vistaIndex;
	}	
	
	@RequestMapping(value="/search")
	public String buscar(@ModelAttribute("search") Tarea tarea, @RequestParam String action) {
		
		busqueda=true;

		if (action.equals("en espera"))	{  
			listaEsperaB = getLista("en espera", tarea.getTarea());
		}else {
			listaActivaB = getLista("Activo",tarea.getTarea());
		}
		
		return "redirect:/"+vistaIndex;
	}
	
	
	@RequestMapping(value="/alta")
	public String alta(@ModelAttribute("alta") Tarea tarea //, @ModelAttribute("registro") Registro registro  // no hace falta.
			, @RequestParam String action ) {
		System.out.println(" Tarea a Guardar ... "+tarea.toString());
		tarea.setCliente("kike");
		tarea.setSolucion("Sin solucion");
		tarea.setStatus("Activa");
		tarea.setMunicipio("Guisando");
		try {
			tarea.setf_alta(formatear.parse("31-07-2020"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (tareaService.guardar(tarea)) {
			System.out.println(" -- Tarea Guardada en BBDD --");
		}else {
			System.out.println(" -- Tarea no Guardada en BBDD");
		}
		tarea.setId(proximoId());
		tarea.setf_alta(new Date());
		if (action.equals("en espera"))	{  
		
			System.out.println("-- Alta de tarea en espera: "+tarea.toString());
			listaEspera.add(tarea);
			
			

		}else {
			listaActiva.add(tarea);
			System.out.println("-- Alta de tarea Activa: "+tarea.toString());
		}
		registros.add(altaRegistro(tarea.getId(),tarea.getDescripcion()+" _Incion Tarea_"));// para recuperar el registro uso el id de la tarea y unico registro activo.
		//System.out.println(" - | lista de registros | - "+registros.toString());
		
		return "redirect:/"+vistaIndex;
	}

	@GetMapping(value="/changeStatus/{id}")
	public String changeStatus(@PathVariable("id") int id, Model vista , @ModelAttribute("registro") Registro registro) {
		
		if (!excelIsClose(ruta)) {// cierro el excel de registros
			// pendiente validacion informando al usuario que debe cerrar el excel
			System.out.println(" ---- Cerrar Excel ---");
			
			try {
				String [] cmd = {"taskkill","/IM","excel.exe"}; 
				Runtime.getRuntime().exec(cmd); 
			} catch (IOException ioe) {
				System.out.println (ioe);
			}

		if (!excelIsClose(ruta)) return "redirect:/"+vistaIndex;// valido que esta cerrado
		}
		System.out.println(" -- Lista espera -- "+listaEspera.toString());
		
			for(int i=0; i< listaEspera.size() ;i++) {
				//System.out.println(" -- i "+ i );
				if (listaEspera.get(i).getId() == id) {
					listaEspera.get(i).setStatus("Activo");
					listaActiva.add(listaEspera.get(i));
					
					// buscar el id del registro. por el id tarea mas activo.
					// buscar la posicion del registro
					// Se guarda la fecha fin y se da de alta un registro nuevo.
					System.out.println(" ------- Registros");
					for (int x=0; x< registros.size(); x++) {
						System.out.println(" ------- id registro > "+registros.get(x).getId());
						if(registros.get(x).getId_todo() == id && registros.get(x).isActivo()) {
							Tarea tarea = new Tarea();
							tarea = listaEspera.get(i);
							registro.setDescripcion("Iniciamos Registro");
							// el paso de activar tarea no lo registro en el excel
							//saveFinRegistro(x,listaEspera.get(i), registro); // Guardamos el registro y le quitamos el activo(predeterminado) Y en el excel
						}
					}
					// falta la descripción
					registros.add(altaRegistro(listaEspera.get(i).getId(),"Fin registro en espera"));// add fuera del buble para que no crezce infinitamente.
					listaEspera.remove(i); // borro la tarea despues de crear el nuevo registro para no perder la referencia.

					System.out.println(" - | lista de registros | - "+registros.toString());				
					System.out.println(" - | lista en espera 	| - "+listaEspera.toString());
					System.out.println(" - | lista activo 		| - "+listaActiva.toString());
					return "redirect:/"+vistaIndex;
				}
			}
			
	//	}else {
			for(int i=0; i< listaActiva.size() ;i++) {
				if (listaActiva.get(i).getId() == id) {
					listaActiva.get(i).setStatus("en espera");
					listaEspera.add( listaActiva.get(i));
					
					// buscar el id del registro. por el id tarea mas activo.
					// buscar la posicion del registro
					// Se guarda la fecha fin y se da de alta un registro nuevo.
					System.out.println(" ------- Registros");
					for (int x=0; x< registros.size(); x++) {
						System.out.println(" ------- id registro > "+registros.get(x).getId());
						if(registros.get(x).getId_todo() == id && registros.get(x).isActivo()) {
							saveFinRegistro(x, listaEspera.get(i),registro); // Guardamos el registro y le quitamos el activo(predeterminado)	
						}
						
					}
					registros.add(altaRegistro(listaActiva.get(i).getId(), registro.getDescripcion()));
					listaActiva.remove(i);// borro la tarea despues de crear el nuevo registro para no perder la referencia.
					
					System.out.println(" - | lista de registros | - "+registros.toString());
					System.out.println(" - | lista en espera | - "+listaEspera.toString());
					System.out.println(" - | lista activo | - "+listaActiva.toString());
				}
			}			
	//	}
		// recalcular las listas segun el estado tras el cambio de estado
		
		
	//	vista.addAttribute("tareasEnEpera", listaEspera);
		//lista.clear();
	//	vista.addAttribute("tareasActivas", listaActiva);

		
		return "redirect:/"+vistaIndex;
	}
	
	@GetMapping("/deleteTarea/{id}")
	String deleteTarea(@PathVariable("id") int id) {
		
		for(int i=0; i< listaEspera.size() ;i++) {
			if (listaEspera.get(i).getId() == id) {

				listaEspera.remove(i);
				System.out.println(" --> Detele id: "+ id);
				System.out.println(" - | lista en espera | - "+listaEspera.toString());
				System.out.println(" - | lista activo | - "+listaActiva.toString());
				carga_inicial=false;
				return "redirect:/"+vistaIndex;
			}
		}
		for(int i=0; i< listaActiva.size() ;i++) {
			if (listaActiva.get(i).getId() == id) {
				listaActiva.remove(i);
				System.out.println(" --> Detele id: "+ id);
				System.out.println(" - | lista en espera | - "+listaEspera.toString());
				System.out.println(" - | lista activo | - "+listaActiva.toString());
				carga_inicial=false;
				return "redirect:/"+vistaIndex; 
			}
		}		
		System.out.println(" --> No se borro nada");
		return "redirect:/"+vistaIndex;
	}

	
	@GetMapping("/editarTarea/{id}")
	String editarTarea(@PathVariable("id") int id,@ModelAttribute("edit") Tarea tarea) {
		System.out.println(" Editando tarea: "+tarea.toString());
		for(int i=0; i< listaEspera.size() ;i++) {
			if (listaEspera.get(i).getId() == id) {
				listaEspera.get(i).setStatus("Editado");
				listaEspera.get(i).setCliente(tarea.getCliente());
				listaEspera.get(i).setDescripcion(tarea.getDescripcion());
				listaEspera.get(i).setf_alert(tarea.getf_alert());
				listaEspera.get(i).setf_alta(tarea.getf_alta());
				listaEspera.get(i).setPrioridad(tarea.getPrioridad());
				listaEspera.get(i).setSolucion(tarea.getSolucion());
				listaEspera.get(i).setTarea(tarea.getTarea());
				listaEspera.add(listaEspera.get(i));
				listaEspera.remove(i);
				System.out.println(" - | lista en espera | - "+listaEspera.toString());
				System.out.println(" - | lista activo | - "+listaActiva.toString());
				return "redirect:/"+vistaIndex;
			}
		}
		for(int i=0; i< listaActiva.size() ;i++) {
			if (listaActiva.get(i).getId() == id) {
				listaActiva.get(i).setStatus("Editado");
				listaActiva.get(i).setCliente(tarea.getCliente());
				listaActiva.get(i).setDescripcion(tarea.getDescripcion());
				listaActiva.get(i).setf_alert(tarea.getf_alert());
				listaActiva.get(i).setf_alta(tarea.getf_alta());
				listaActiva.get(i).setPrioridad(tarea.getPrioridad());
				listaActiva.get(i).setSolucion(tarea.getSolucion());
				listaActiva.get(i).setTarea(tarea.getTarea());
				listaActiva.add( listaActiva.get(i));
				listaActiva.remove(i);
				System.out.println(" - | lista en espera | - "+listaEspera.toString());
				System.out.println(" - | lista activo | - "+listaActiva.toString());
			}
		}	
		
		System.out.println(" --> Edit id: "+ id);
		return "redirect:/"+vistaIndex;
	}

	
	@GetMapping("/resolverTarea/{id}")
	String resolverTarea(@PathVariable("id") int id) {
		System.out.println(" --> Resolver id: "+ id);
		
		for(int i=0; i< listaEspera.size() ;i++) {
			if (listaEspera.get(i).getId() == id) {
				listaEspera.get(i).setStatus("Resuelto");
				listaEspera.add(listaEspera.get(i));
				listaEspera.remove(i);
				System.out.println(" - | lista en espera | - "+listaEspera.toString());
				System.out.println(" - | lista activo | - "+listaActiva.toString());
				return "redirect:/"+vistaIndex;
			}
		}
		for(int i=0; i< listaActiva.size() ;i++) {
			if (listaActiva.get(i).getId() == id) {
				listaActiva.get(i).setStatus("Resuelto");
				listaActiva.add( listaActiva.get(i));
				listaActiva.remove(i);
				System.out.println(" - | lista en espera | - "+listaEspera.toString());
				System.out.println(" - | lista activo | - "+listaActiva.toString());
			}
		}	
		return "redirect:/"+vistaIndex;
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
			tarea1.setf_alta(formatear.parse("31-07-2020"));
			tarea1.setf_alert(formatear.parse("23-08-2020"));
			tarea1.setStatus("en espera");
			tarea1.setDescripcion("Cambio de servidor");
			
			Tarea tarea2 = new Tarea();
			tarea2.setId(2);
			tarea2.setTarea("Desarrollo");
			tarea2.setPrioridad("Alta");
			tarea2.setCliente("kike");
			tarea2.setf_alta(formatear.parse("31-07-2020"));
			tarea2.setf_alert(formatear.parse("23-08-2020"));
			tarea2.setStatus("Activo");
			tarea2.setDescripcion("Crear app web to-do");
			
			
			Tarea tarea3 = new Tarea();
			tarea3.setId(3);
			tarea3.setTarea("Desarrollo");
			tarea3.setPrioridad("Alta");
			tarea3.setCliente("kike");
			tarea3.setf_alta(formatear.parse("31-07-2020"));
			tarea3.setf_alert(formatear.parse("23-08-2020"));
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
			tarea1.setf_alta(formatear.parse("31-07-2020"));
			tarea1.setf_alert(formatear.parse("23-08-2020"));
			tarea1.setStatus("en espera");
			tarea1.setDescripcion("Cambio de servidor");
			
			Tarea tarea2 = new Tarea();
			tarea2.setId(2);
			tarea2.setTarea("Desarrollo");
			tarea2.setPrioridad("Alta");
			tarea2.setCliente("kike");
			tarea2.setf_alta(formatear.parse("31-07-2020"));
			tarea2.setf_alert(formatear.parse("23-08-2020"));
			tarea2.setStatus("Activo");
			tarea2.setDescripcion("Crear app web to-do");
			
			
			Tarea tarea3 = new Tarea();
			tarea3.setId(3);
			tarea3.setTarea("Desarrollo");
			tarea3.setPrioridad("Alta");
			tarea3.setCliente("kike");
			tarea3.setf_alta(formatear.parse("31-07-2020"));
			tarea3.setf_alert(formatear.parse("23-08-2020"));
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
	private List<Registro> getRegistrosByTarea(int id_tarea){
		List<Registro> rHistorico = new LinkedList<Registro>();
		for (int x=0; x< registros.size(); x++) {
			System.out.println(" ------- registro > "+registros.get(x).toString());
			if(registros.get(x).getId_todo() == id_tarea ) {
				rHistorico.add(registros.get(x));
			}
		}
		
		return rHistorico;
	}
	
	private List<Tarea> getLista(int id){
		System.out.println(" GetLista Status");
		
		
		List<Tarea> lista = new LinkedList<Tarea>();
		
		try {
			
			Tarea tarea1 = new Tarea();
			tarea1.setId(1);
			tarea1.setTarea("Soporte");
			tarea1.setPrioridad("Alta");
			tarea1.setCliente("Lorca");
			tarea1.setf_alta(formatear.parse("31-07-2020"));
			tarea1.setf_alert(formatear.parse("23-08-2020"));
			tarea1.setStatus("en espera");
			tarea1.setDescripcion("Cambio de servidor");
			
			Tarea tarea2 = new Tarea();
			tarea2.setId(2);
			tarea2.setTarea("Desarrollo");
			tarea2.setPrioridad("Alta");
			tarea2.setCliente("kike");
			tarea2.setf_alta(formatear.parse("31-07-2020"));
			tarea2.setf_alert(formatear.parse("23-08-2020"));
			tarea2.setStatus("Activo");
			tarea2.setDescripcion("Crear app web to-do");
			
			
			Tarea tarea3 = new Tarea();
			tarea3.setId(3);
			tarea3.setTarea("Desarrollo");
			tarea3.setPrioridad("Alta");
			tarea3.setCliente("kike");
			tarea3.setf_alta(formatear.parse("31-07-2020"));
			tarea3.setf_alert(formatear.parse("23-08-2020"));
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
	public Registro altaRegistro(int id_tarea, String descripcion) {
		Registro registro = new Registro();
		registro.setId(id_registro+=1);
		registro.setId_todo(id_tarea);
		registro.setF_inicio(new Date());
		registro.setActivo(true);
		registro.setDescripcion(descripcion);
		
		return registro;
	}
	/*
	 *  Pendiente modificar la vista con la descripción del registro.
	 */
	
	public void saveFinRegistro (int posicionReg, Tarea tarea, Registro registro) {
		registros.get(posicionReg).setF_fin(new Date());
		registros.get(posicionReg).setActivo(false);
		// --
		System.out.println("-- Save Fin Registro -- Id "+registro.getId()+" -- Id Todo "+ registros.get(posicionReg).getId_todo()+" Fecha inicio "+ registros.get(posicionReg).getF_inicio()+" Fecha fin "+ registros.get(posicionReg).getF_fin()+" Descripcion "+registro.getDescripcion());
		CrearFicherosExcel.nextRecord(registro.getId()+"",
				registros.get(posicionReg).getId_todo()+"",registros.get(posicionReg).getF_inicio()+"",
				registros.get(posicionReg).getF_fin()+"",
				registro.getDescripcion()+"");
	}
	
	public boolean excelIsClose(String ruta) {
		try {
			FileInputStream inputStream = new FileInputStream(new File(ruta));
			Workbook libro= WorkbookFactory.create(inputStream);
			
			inputStream.close();
			
			FileOutputStream outputStream = new FileOutputStream(ruta);
			libro.write(outputStream);
			
			libro.close();
			outputStream.close();
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			
			e.printStackTrace();
			return false;
		} catch (EmptyFileException e) {
			e.printStackTrace(); 
			CrearFicherosExcel.inicial();// Esta vacio o corrupto. Se crea de nuevo.
			return true;
		}
		return true;
	}
	
	
}
