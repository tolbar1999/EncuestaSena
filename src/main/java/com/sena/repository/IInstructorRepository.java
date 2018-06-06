package com.sena.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sena.modelo.Instructor;

@Repository
public interface IInstructorRepository extends JpaRepository<Instructor, Integer>{

	@Query("select i.id from Instructor i where i.cedula = :cedula")
	public Integer existeCedula(@Param("cedula") String cedula); 
	
}
