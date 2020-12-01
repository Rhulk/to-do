package com.ecarvajal.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecarvajal.model.Cliente;
import com.ecarvajal.repository.ClienteRepository;
import com.ecarvajal.service.impl.IClienteService;

@Service
public class ClienteService implements IClienteService{

	@Autowired
	private ClienteRepository repoClient;
	
	@Override
	public boolean  guardar(Cliente cliente) {
        try {
        	repoClient.save(cliente);
            return true;
       } catch (Exception e) {
            return false;
       }
	}

	@Override
	public Optional<Cliente> burcarPorID(Integer id) {
		// TODO Auto-generated method stub
		return repoClient.findById(id);
	}

	@Override
	public List<Cliente> buscarTodos() {
		// TODO Auto-generated method stub
		return repoClient.findAll();
	}

	@Override
	public boolean borrarPorID(Integer id) {
		// TODO Auto-generated method stub
		try {
			repoClient.deleteById(id);
			return true;			
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(" -- No se borro por: "+e.getMessage());
			return false;
		}

	}


}
