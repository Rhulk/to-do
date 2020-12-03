package com.ecarvajal.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ecarvajal.model.Cliente;
import com.ecarvajal.model.Mantenimiento;
import com.ecarvajal.service.ClienteService;
import com.ecarvajal.service.EmailSenderService;
import com.ecarvajal.service.Emails;
import com.ecarvajal.service.Listas;

@Controller
public class ClientesController {

	@Autowired
	Listas list = new Listas();
	EmailSenderService email;
	
	public int id_detalle;
	public boolean carga_inicial=true;
	public boolean mtn_inicial=true;
	public boolean busqueda=false;
	public boolean busq_mant=false;
	public String detallecliente="detalleclienteInicial";
	
	List<Cliente> clientes = new LinkedList<Cliente>();
	List<Cliente> clientesB = new LinkedList<Cliente>();
	List<Mantenimiento> mant = new LinkedList<Mantenimiento>();
	List<Mantenimiento> mantB = new LinkedList<Mantenimiento>();
	
	SimpleDateFormat formatear = new SimpleDateFormat("dd-mm-yyyy");
	
	@Autowired
	ClienteService clienteService = new ClienteService();
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
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
		model.addAttribute("search_cliente",new Cliente());
		model.addAttribute("alta_cliente", new Cliente());
		model.addAttribute("alta_mtn", new Mantenimiento());
		model.addAttribute("search_mtn", new Mantenimiento());
	}
	
	@GetMapping("/listclientes")
	public String list(Model vista) {
		System.out.println("Vista listado clientes");
		
		
		if (busqueda) {
			busqueda=false;
			vista.addAttribute("clientes", clientesB);
			
		}else {
			vista.addAttribute("clientes", clienteService.buscarTodos());// recuperamos los clientes seteados.
		}		
		
		return "clientes/list";
	}
	
	@RequestMapping(value="/alta_cliente")
	public String alta(@ModelAttribute("alta_cliente") Cliente cliente) {
		System.out.println(" -- Alta cliente -- Controller --");
	//	cliente.setId(proximoId());
		if(clienteService.guardar(cliente)) {
			System.out.println(" Cliente Grabado en la BBDD");
		}{
			System.out.println(" No se grabo el cliente en la BBDD");
		}
		
		return "redirect:/"+"listclientes";
	}
	
	@RequestMapping(value="/alta_mtn")
	public String altaMtn(@ModelAttribute("alta_mtn") Mantenimiento mtn) {
		System.out.println(" -- Alta Mantenimiento -- Controller --");
		mtn.setId(proximoIdMtn());
		mtn.setFalta(new Date());
		mant.add(mtn);
		return "redirect:/"+"detallecliente";
	}
	
	@RequestMapping("/searchClientes")
	public String buscarCliente(@ModelAttribute("search_cliente") Cliente cliente, BindingResult result) {
		System.out.println(" -- Search Clientes --");
		if(result.hasErrors()) {
			// fuerzo llegar al controlador aunque tenga campos del producto vacios.
			System.out.println(" -- Hay errores --");
		}	
		clientesB.clear();
		for(int index=0; index < clientes.size(); index++ ) {
			if(cliente.nombre.equals(clientes.get(index).getNombre()) || cliente.getNombre().isEmpty() ) {
				if(cliente.getApellido1().equals(clientes.get(index).getApellido1()) || cliente.getApellido1().isEmpty() ) {
					if(cliente.getApellido2().equals(clientes.get(index).getApellido2()) || cliente.getApellido2().isEmpty() ) {
						if(cliente.getEmail().equals(clientes.get(index).getEmail()) || cliente.getEmail().isEmpty() ) {
							if(cliente.getMunicipio().equals(clientes.get(index).getMunicipio()) || cliente.getMunicipio().isEmpty() ) {
								if(cliente.getDireccion().equals(clientes.get(index).getDireccion()) || cliente.getDireccion().isEmpty() ) {
									if(cliente.getObservaciones().equals(clientes.get(index).getObservaciones()) || cliente.getObservaciones().isEmpty() ) {
										if(cliente.getTelefono() == clientes.get(index).getTelefono() || cliente.getTelefono() == 0 ) {
											clientesB.add(clientes.get(index));
										}											
									}										
								}									
							}							
						}			
					}						
				}				
			}
		}
		busqueda=true;
		return "redirect:/"+"listclientes";
	}
	@RequestMapping("/searchMantenimientos")
	public String buscarMantenimientos(@ModelAttribute("search_mantenimiento") Mantenimiento mantenimiento, BindingResult result) {
		System.out.println(" -- Search Mantenimientos --");
		mantB.clear();
		if(result.hasErrors()) {
			// fuerzo llegar al controlador aunque tenga campos del producto vacios.
			System.out.println(" -- Hay errores --");
		}
		// generamos la list B con las coincidencias de la busqueda.
		System.out.println("-- mant.size :"+mant.size());
		for(int index=0; index < mant.size(); index ++) {
			if(mantenimiento.getDescripcion() == null) {
				mantenimiento.setDescripcion("");
			}
			System.out.println(" Fecha busqueda: "+mantenimiento.getFalerta());
			System.out.println(" Fecha encontrada: "+mant.get(index).falerta);
			//borrar la hora y minutos
			mant.get(index).getFalerta().setHours(0);
			mant.get(index).getFalerta().setMinutes(0);
			
			// para que no interfieran las horas y minutos en la busqueda mientras doy con el origen de estas horas y minutos.
			if (mantenimiento.falerta != null) {
				mantenimiento.getFalerta().setHours(0);
				mantenimiento.getFalerta().setMinutes(0);
			}
				
			System.out.println(" Descripcion: "+mantenimiento.getDescripcion()+"| ");
			

			if(mantenimiento.descripcion.contains(mant.get(index).descripcion) || mantenimiento.getDescripcion().isEmpty() ) {
				if(mantenimiento.getFalerta() == null) {
					mantB.add(mant.get(index));
				}else {
					if(mantenimiento.falerta.equals(mant.get(index).falerta)) {
						mantB.add(mant.get(index));
					}						
				}
			
			}
			
		}
		
		busq_mant=true;
		return "redirect:/"+"detallecliente";
	}
	
	@GetMapping("/deleteCliente/{id}")
	String deleteCliente(@PathVariable("id") int id) {
		System.out.println(" -- Eliminado cliente -- "+id);
		try {
			clienteService.borrarPorID(id);
		}catch (Exception e) {
			System.out.println(" No se elimino: "+e.getMessage());
			// TODO: handle exception
		}
		return "redirect:/"+"listclientes";
	}
	
	@GetMapping("/deleteMtn/{id}")
	String deleteMantenimiento(@PathVariable("id") int id) {
		System.out.println(" -- Eliminado Mantenimiento -- "+id);
		for (int i=0; i< mant.size(); i++) {
			if (mant.get(i).getId() == id) {
				mant.remove(i);
			}
		}
		return "redirect:/"+"detallecliente";
	}
	

	
	@GetMapping("/detallecliente/{id}")
	public String detalle(@PathVariable("id") int id) {
		System.out.println(" -- Detalle cliente vista id:"+ id);
		id_detalle=id;
		detallecliente="detalleclienteInicial";
		return "redirect:/"+"detallecliente";
	}
	
	@GetMapping("/detalleOcultoTarea/{id}")
	public String detalleOcultoTarea(@PathVariable("id") int id) {
		System.out.println(" -- Detalle cliente Taller id:"+ id);
		id_detalle=id;
		detallecliente="detalleOcultoTarea";
		return "redirect:/"+"detallecliente";
	}
	
	@GetMapping("/detalleOcultoMtn/{id}")
	public String detalleOcultoMtn(@PathVariable("id") int id) {
		System.out.println(" -- Detalle cliente Mantenimiento id:"+ id);
		id_detalle=id;
		detallecliente="detalleOcultoMtn";
		return "redirect:/"+"detallecliente";
	}
	
	@GetMapping("/detalleOcultoVenta/{id}")
	public String detalleOcultoVenta(@PathVariable("id") int id) {
		System.out.println(" -- Detalle cliente Venta id:"+ id);
		id_detalle=id;
		detallecliente="detalleOcultoVenta";
		return "redirect:/"+"detallecliente";
	}
	
	
	@GetMapping("/detallecliente")
	public String detail(Model vista) {
		int id=id_detalle;
		// carga inicial por cliente
		if (mtn_inicial) {
			System.out.println(" -- Carga mantenimientos para el cliente --");
			mant = list.getMantenimientos(id_detalle);
			mtn_inicial=false;
		}
		// buscamos al cliente
		for (int i=0; i < clientes.size() ; i++ ) {
			if(clientes.get(i).getId() == id) {
				vista.addAttribute("cliente", clientes.get(i) );
				
				// implementear busqueda mantenimiento.
				if (busq_mant) {
					vista.addAttribute("mante", mantB);
					busq_mant= false;
				}else {
					vista.addAttribute("mante", mant);
				}
				
				
				
				email = new EmailSenderService();
				email.start(); // enviamos email con un hilo aparte para no paralizar la aplicación.
				
		/*		Emails e = new Emails();
				try {
					e.enviar("quique1904@gmail.com", "Hola Mundo! Soy un mensaje!", "ejemplo de email enviado con Jakarta Mail");
				} catch (MessagingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}*/
			}
		}
		return "clientes/"+detallecliente;
	}
	

	
	
	
	public int proximoId() {
		int id =0;
		
		for(int i=0; i< clientes.size() ;i++) {
			if (clientes.get(i).getId() > id) id =clientes.get(i).getId();
		}

		return id+=1;
	}
	public int proximoIdMtn() {
		int id =0;
		
		for(int i=0; i< mant.size() ;i++) {
			if (mant.get(i).getId() > id) id =mant.get(i).getId();
		}

		return id+=1;
	}

}
