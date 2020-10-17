package com.ecarvajal.service;


import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ecarvajal.model.Cliente;
import com.ecarvajal.model.Producto;


@Service
public class Listas {
	
	/*
	 * Lista ficticia auto generada ficticia temporal.
	 * 
	 * 
	 */
	public List<Cliente> getClientes(){
		System.out.println(" GetLista Clientes ");
		//SimpleDateFormat formatear = new SimpleDateFormat("dd-mm-yyyy");
		List<Cliente> lista = new LinkedList<Cliente>();
		
		try {
			
			Cliente cliente1 = new Cliente();
			cliente1.setId(1);
			cliente1.setNombre("Fran");
			cliente1.setApellido1("Garcia");
			cliente1.setApellido2("Garcia");
			cliente1.setDireccion("Lepanto 9");
			cliente1.setEmail("FranGG@gmail.com");
			cliente1.setTelefono(67565432);
			cliente1.setObservaciones("Muy majo");
			cliente1.setMunicipio("San Lorenzo");
			
			Cliente cliente2 = new Cliente();
			cliente2.setId(2);
			cliente2.setNombre("Marck");
			cliente2.setApellido1("Jara");
			cliente2.setApellido2("Ruiz");
			cliente2.setDireccion("Lepanto 4");
			cliente2.setEmail("MarckJR@gmail.com");
			cliente2.setTelefono(67455432);
			cliente2.setObservaciones("---");
			cliente2.setMunicipio("Lorca");
			
			
			Cliente cliente3 = new Cliente();
			cliente3.setId(3);
			cliente3.setNombre("Maria");
			cliente3.setApellido1("Ventero");
			cliente3.setApellido2("Martin");
			cliente3.setDireccion("Carlos II");
			cliente3.setEmail("MariaVC@gmail.com");
			cliente3.setTelefono(63465432);
			cliente3.setObservaciones(":)");
			cliente3.setMunicipio("Cieza");

			
			lista.add(cliente1);
			lista.add(cliente2);
			lista.add(cliente3);
			

			System.out.println(" Tamaño lista: "+lista.size());
			return lista;
			
		}catch(NullPointerException nu) {
			System.out.println(" Localizacion "+nu.getLocalizedMessage());
			System.out.println(" Mensaje : "+nu.getMessage());
			System.out.println(" Causa : "+nu.getCause());
			System.out.println(" Error : "+nu);
			return null;
		}
		
	}
	
	/*
	 * Lista ficticia auto generada ficticia temporal.
	 * 
	 * 
	 */
	public List<Producto> getProductos(){
		System.out.println(" GetLista Productos ");
		//SimpleDateFormat formatear = new SimpleDateFormat("dd-mm-yyyy");
		List<Producto> lista = new LinkedList<Producto>();
		
		try {
			
			Producto producto1 = new Producto();
			producto1.setId(1);
			producto1.setCodProducto("135.1956");
			producto1.setNombre("A2 MIPS 2019 LTD ADIDAS TEAM NAVY / LIGH");
			producto1.setPrecio(153.30);
			producto1.setCategoria("Equipamiento");
			producto1.setSubCategoria("Casco");
			producto1.setStock(4);
			producto1.setPublicado(false);
			
			Producto producto2 = new Producto();
			producto2.setId(2);
			producto2.setCodProducto("135.1954");
			producto2.setNombre("RODILLERA FOX LAUNCH PRO D30 2020 NEGRO");
			producto2.setPrecio(130.30);
			producto2.setCategoria("Equipamiento");
			producto2.setSubCategoria("Rodillera");
			producto2.setStock(4);
			producto2.setPublicado(true);
			
			Producto producto3 = new Producto();
			producto3.setId(3);
			producto3.setCodProducto("135.1952");
			producto3.setNombre("GUANTE TROY LEE AIR GLOVE 2019 RED L");
			producto3.setPrecio(24.50);
			producto3.setCategoria("Equipamiento");
			producto3.setSubCategoria("Guantes");
			producto3.setStock(5);
			producto3.setPublicado(true);
		
			lista.add(producto1);
			lista.add(producto2);
			lista.add(producto3);
			

			System.out.println(" Tamaño lista: "+lista.size());
			return lista;
			
		}catch(NullPointerException nu) {
			System.out.println(" Localizacion "+nu.getLocalizedMessage());
			System.out.println(" Mensaje : "+nu.getMessage());
			System.out.println(" Causa : "+nu.getCause());
			System.out.println(" Error : "+nu);
			return null;
		}
		
	}
	
}
