package com.ecarvajal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecarvajal.model.Tarea;
import com.ecarvajal.repository.TareaRepository;
import com.ecarvajal.service.impl.ITareaService;

@Service
public class TareaService implements ITareaService {
	
	@Autowired
	private TareaRepository repo;

	@Override
	public boolean guardar(Tarea tarea) {
		try {
			System.out.println(tarea.toString());
			repo.save(tarea);
			return true;
		}catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

}
