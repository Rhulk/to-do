package com.ecarvajal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecarvajal.model.Modelo;

public interface ModeloRepository extends JpaRepository<Modelo, Integer> {

}
