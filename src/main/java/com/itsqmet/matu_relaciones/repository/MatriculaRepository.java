package com.itsqmet.matu_relaciones.repository;

import com.itsqmet.matu_relaciones.model.matricula;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MatriculaRepository extends JpaRepository<matricula, Long> {

    List<matricula> findByEstudianteId(Long estudianteId);
    List<matricula> findByCursoId(Long cursoId);

}