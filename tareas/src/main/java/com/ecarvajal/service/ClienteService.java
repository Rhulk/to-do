package com.ecarvajal.service;

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

}
