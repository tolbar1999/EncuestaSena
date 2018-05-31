package com.sena.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sena.modelo.Ficha;

@Repository
public interface IFichaRepository extends JpaRepository<Ficha, Integer>{

}
