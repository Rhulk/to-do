package com.ecarvajal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ecarvajal.model.Tarea;

public interface TareaRepository extends JpaRepository<Tarea, Integer> {

}
