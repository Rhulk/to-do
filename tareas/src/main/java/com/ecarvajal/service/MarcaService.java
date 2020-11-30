package com.ecarvajal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecarvajal.model.Marca;
import com.ecarvajal.repository.MarcaRepository;
import com.ecarvajal.service.impl.IMarcaService;

@Service
public class MarcaService implements IMarcaService {

	@Autowired
	public MarcaRepository repo;
	
	@Override
	public boolean guardar(Marca marca) {
		try {
			repo.save(marca);
			return true;
		}catch (Exception e) {
			System.out.println(" -- NO se guardo la marca --");
			return false;
		}
	}

}
