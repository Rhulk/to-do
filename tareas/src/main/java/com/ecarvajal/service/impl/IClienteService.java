package com.ecarvajal.service.impl;


import java.util.List;
import java.util.Optional;

import com.ecarvajal.model.Cliente;


public interface IClienteService {
	boolean guardar(Cliente cliente);
	Optional<Cliente> burcarPorID(Integer id);
	List<Cliente> buscarTodos();
	boolean borrarPorID(Integer id);
	
	
}
