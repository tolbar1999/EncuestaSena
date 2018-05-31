package com.sena.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sena.modelo.Evaluacion;

@Repository
public interface IEvaluacionRepository extends JpaRepository<Evaluacion, Integer>{

}
