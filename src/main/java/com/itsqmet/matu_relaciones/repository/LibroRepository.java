package com.itsqmet.matu_relaciones.repository;

import com.itsqmet.matu_relaciones.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface LibroRepository extends JpaRepository<Libro, Long> {
    Optional<Libro> findByIsbn(String isbn);
    List<Libro> findByAutorId(Long autorId);
}
