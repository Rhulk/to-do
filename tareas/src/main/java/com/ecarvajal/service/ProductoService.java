package com.ecarvajal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecarvajal.model.Producto;
import com.ecarvajal.repository.ProductoRepository;
import com.ecarvajal.service.impl.IProductoService;

@Service
public class ProductoService implements IProductoService {

	@Autowired
	private ProductoRepository repo;
	
	@Override
	public boolean guardar(Producto producto) {
		try {
			repo.save(producto);
			return true;
		}catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

}
