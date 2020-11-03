package com.ecarvajal.controller;

import java.util.LinkedList;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ecarvajal.model.Cliente;
import com.ecarvajal.model.Mantenimiento;
import com.ecarvajal.service.EmailSenderService;
import com.ecarvajal.service.Emails;
import com.ecarvajal.service.Listas;

@Controller
public class ClientesController {

	@Autowired
	Listas list = new Listas();
	EmailSenderService email = new EmailSenderService();
	
	public int id_detalle;
	public boolean carga_inicial=true;
	public boolean busqueda=false;
	
	List<Cliente> clientes = new LinkedList<Cliente>();
	List<Mantenimiento> mant = new LinkedList<Mantenimiento>();
	
	/*
	 * IMPORTANTE Aqui le estoy pasando el modelo a la vista para tener declarada la variable search en el form de la vista para la busqueda
	 * 
	 * 	
	 */
	@ModelAttribute
	public void setGenericos(Model model) {
		model.addAttribute("search_cliente",new Cliente());
		model.addAttribute("alta_cliente", new Cliente());
	}
	
	@RequestMapping(value="/alta_cliente")
	public String alta(@ModelAttribute("alta_cliente") Cliente cliente) {
		System.out.println(" -- Alta cliente -- Controller --");
		cliente.setId(proximoId());
		clientes.add(cliente);
		return "redirect:/"+"listclientes";
	}
	
	@GetMapping("/deleteCliente/{id}")
	String deleteCliente(@PathVariable("id") int id) {
		System.out.println(" -- Eliminado cliente -- "+id);
		for (int i=0; i< clientes.size(); i++) {
			if (clientes.get(i).getId() == id) {
				clientes.remove(i);
			}
		}
		return "redirect:/"+"listclientes";
	}
	
	@GetMapping("/listclientes")
	public String list(Model vista) {
		System.out.println("Vista listado clientes");
		
		if (clientes.isEmpty()  && carga_inicial) {
			System.out.println("Lista vacia de clientes se inicializa ...");
			//Carga inicial de datos.
			clientes = list.getClientes();
		}
		
		if (busqueda) {
			busqueda=false;
			//vista.addAttribute("tareasEnEpera", listaEsperaB);
			//vista.addAttribute("tareasActivas", listaActivaB);
			
		}else {
			//vista.addAttribute("tareasEnEpera", listaEspera);
			//vista.addAttribute("tareasActivas", listaActiva);
		}		
		

		vista.addAttribute("clientes", clientes);// recuperamos los clientes seteados.
		
		return "clientes/list";
	}
	
	@GetMapping("/detallecliente/{id}")
	public String detalle(@PathVariable("id") int id) {
		System.out.println(" -- Detalle cliente vista id:"+ id);
		id_detalle=id;
		return "redirect:/"+"detallecliente";
	}
	
	@GetMapping("/detallecliente")
	public String detail(Model vista) {
		int id=id_detalle;
		// buscamos al cliente
		for (int i=0; i < clientes.size() ; i++ ) {
			if(clientes.get(i).getId() == id) {
				vista.addAttribute("cliente", clientes.get(i) );
				vista.addAttribute("mante", list.getMantenimientos(id_detalle));
				email.sendEmail(); // enviamos email
				
		/*		Emails e = new Emails();
				try {
					e.enviar("quique1904@gmail.com", "Hola Mundo! Soy un mensaje!", "ejemplo de email enviado con Jakarta Mail");
				} catch (MessagingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}*/
			}
		}
		return "clientes/detallecliente";
	}
	
	
	public int proximoId() {
		int id =0;
		
		for(int i=0; i< clientes.size() ;i++) {
			if (clientes.get(i).getId() > id) id =clientes.get(i).getId();
		}

		return id+=1;
	}

}
