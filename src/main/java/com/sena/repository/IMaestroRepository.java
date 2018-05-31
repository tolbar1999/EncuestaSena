package com.sena.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sena.modelo.Maestro;

@Repository
public interface IMaestroRepository extends JpaRepository<Maestro, Integer>{

}
