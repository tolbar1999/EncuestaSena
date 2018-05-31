package com.sena.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sena.modelo.Instructor;

@Repository
public interface IInstructorRepository extends JpaRepository<Instructor, Integer>{

}
