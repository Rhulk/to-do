package com.ecarvajal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecarvajal.model.Registro;

public interface RegistroRepository extends JpaRepository<Registro, Integer>{

}
