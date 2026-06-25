package com.itsqmet.matu_relaciones.service;

import com.itsqmet.matu_relaciones.model.curso;
import com.itsqmet.matu_relaciones.model.estudiante;
import com.itsqmet.matu_relaciones.repository.CursoRepository;
import com.itsqmet.matu_relaciones.repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EstudianteService {

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Autowired
    private CursoRepository cursoRepository;

    public List<estudiante> obtenerTodos() { return estudianteRepository.findAll(); }

    public Optional<estudiante> obtenerPorId(Long id) { return estudianteRepository.findById(id); }

    public estudiante crear(estudiante estudiante) { return estudianteRepository.save(estudiante); }

    public Optional<estudiante> actualizar(Long id, estudiante datos) {
        return estudianteRepository.findById(id).map(e -> {
            e.setNombre(datos.getNombre());
            e.setApellido(datos.getApellido());
            e.setEmail(datos.getEmail());
            return estudianteRepository.save(e);
        });
    }

    public boolean eliminar(Long id) {
        if (estudianteRepository.existsById(id)) {
            estudianteRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Optional<estudiante> agregarCurso(Long estudianteId, Long cursoId) {
        Optional<estudiante> est = estudianteRepository.findById(estudianteId);
        Optional<curso> cur = cursoRepository.findById(cursoId);

        if (est.isEmpty() || cur.isEmpty()) return Optional.empty();

        est.get().getCursos().add(cur.get());
        return Optional.of(estudianteRepository.save(est.get()));
    }

    public Optional<estudiante> quitarCurso(Long estudianteId, Long cursoId) {
        Optional<estudiante> est = estudianteRepository.findById(estudianteId);
        Optional<curso> cur = cursoRepository.findById(cursoId);

        if (est.isEmpty() || cur.isEmpty()) return Optional.empty();

        est.get().getCursos().removeIf(c -> c.getId().equals(cursoId));
        return Optional.of(estudianteRepository.save(est.get()));
    }
}