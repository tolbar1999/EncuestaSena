package com.sena.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sena.modelo.Ficha;

@Repository
public interface IFichaRepository extends JpaRepository<Ficha, Integer>{

	@Query("select f.id from Ficha f where f.numero = :numero")
	public Integer existeNumero(@Param("numero") String numero);
	
	@Query("select f.numero from Ficha f where f.numero like %:numero%")
	public Page<String[]> autoCompletarNumeroFicha(@Param("numero") String numero, Pageable pageable);
	 
	@Query("select f.id from Ficha f where f.numero = :numero")
	public Integer buscarPorNumeroFicha(@Param("numero") String numero);
	
	@Query("select f.id from Ficha f where f.numero = :numero")
	public Integer existeNumeroInstructorFicha(@Param("numero") String numero);
	
}
