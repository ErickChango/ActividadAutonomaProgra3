package com.itsqmet.matu_relaciones.repository;

import com.itsqmet.matu_relaciones.model.estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface EstudianteRepository extends JpaRepository<estudiante, Long> {
    Optional<estudiante> findByEmail(String email);
}
