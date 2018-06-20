package com.sena.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sena.modelo.InstructorFicha;
import com.sena.modelo.InstructorFichaId;

@Repository
public interface IInstructorFichaRepository extends JpaRepository<InstructorFicha, InstructorFichaId>{
	
	@Transactional
	@Modifying
	@Query("update InstructorFicha if set if.id.idFicha = :idFichaNuevo, if.id.idInstructor = :idInstructorNuevo, if.detalleEstado.id = :idDetalleEstado where if.id = :instructorFichaIdViejo") 
	public void actualizarCustom(@Param("idFichaNuevo") int idFichaNuevo, @Param("idInstructorNuevo") int idInstructorNuevo, @Param("idDetalleEstado") int idDetalleEstado, @Param("instructorFichaIdViejo") InstructorFichaId instructorFichaIdViejo);
	
}
