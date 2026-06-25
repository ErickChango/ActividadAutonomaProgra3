package com.itsqmet.matu_relaciones.service;

import com.itsqmet.matu_relaciones.model.curso;
import com.itsqmet.matu_relaciones.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    public List<curso> obtenerTodos() { return cursoRepository.findAll(); }

    public Optional<curso> obtenerPorId(Long id) { return cursoRepository.findById(id); }

    public curso crear(curso curso) { return cursoRepository.save(curso); }

    public Optional<curso> actualizar(Long id, curso datos) {
        return cursoRepository.findById(id).map(c -> {
            c.setNombre(datos.getNombre());
            c.setDescripcion(datos.getDescripcion());
            c.setNivel(datos.getNivel());
            return cursoRepository.save(c);
        });
    }

    public boolean eliminar(Long id) {
        if (cursoRepository.existsById(id)) {
            cursoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}