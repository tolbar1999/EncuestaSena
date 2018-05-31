package com.sena.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sena.modelo.InstructorFicha;

@Repository
public interface IInstructorFichaRepository extends JpaRepository<InstructorFicha, Integer>{

}
