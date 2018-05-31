package com.sena.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sena.modelo.Pregunta;

@Repository
public interface IPreguntaRepository extends JpaRepository<Pregunta, Integer>{

}
