package com.sena.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sena.modelo.Aprendiz;

@Repository
public interface IAprendizRepository extends JpaRepository<Aprendiz, Integer>{

}
