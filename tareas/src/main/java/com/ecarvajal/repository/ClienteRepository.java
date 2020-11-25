package com.ecarvajal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecarvajal.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

}
