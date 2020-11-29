package com.ecarvajal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecarvajal.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {

}
