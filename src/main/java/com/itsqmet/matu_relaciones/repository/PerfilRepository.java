package com.itsqmet.matu_relaciones.repository;

import com.itsqmet.matu_relaciones.model.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface PerfilRepository extends JpaRepository<Perfil, Long> {
    Optional<Perfil> findByAutorId(Long autorId);
}
