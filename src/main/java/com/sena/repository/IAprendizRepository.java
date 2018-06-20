package com.sena.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sena.modelo.Aprendiz;

@Repository
public interface IAprendizRepository extends JpaRepository<Aprendiz, Integer>{

	@Query("select a.id from Aprendiz a where a.identificacion = :identificacion")
	public Integer existeIdentificacion(@Param("identificacion") String identificacion); 
	
}
