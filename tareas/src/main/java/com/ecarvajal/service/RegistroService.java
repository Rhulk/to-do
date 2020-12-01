package com.ecarvajal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecarvajal.model.Registro;
import com.ecarvajal.repository.RegistroRepository;
import com.ecarvajal.service.impl.IRegistroService;

@Service
public class RegistroService implements IRegistroService {
	
	@Autowired
	private RegistroRepository repo;

	@Override
	public boolean guardar(Registro registro) {
		try {
			repo.save(registro);
			return true;
		}catch (Exception e) {
			System.out.println(" -- No se guardo el registro --");
			return false;
		}
	}

}
