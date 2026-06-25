package com.itsqmet.matu_relaciones.service;

import com.itsqmet.matu_relaciones.model.matricula;
import com.itsqmet.matu_relaciones.model.curso;
import com.itsqmet.matu_relaciones.model.estudiante;
import com.itsqmet.matu_relaciones.repository.MatriculaRepository;
import com.itsqmet.matu_relaciones.repository.CursoRepository;
import com.itsqmet.matu_relaciones.repository.EstudianteRepository;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.awt.Color;
import java.io.OutputStream;
import java.util.List;
import java.util.Optional;

@Service
public class MatriculaService {

    @Autowired private MatriculaRepository matriculaRepository;
    @Autowired private EstudianteRepository estudianteRepository;
    @Autowired private CursoRepository cursoRepository;


    public List<matricula> obtenerTodas() {
        return matriculaRepository.findAll();
    }

    public Optional<matricula> obtenerPorId(Long id) {
        return matriculaRepository.findById(id);
    }

    public List<matricula> obtenerPorEstudiante(Long estudianteId) {
        return matriculaRepository.findByEstudianteId(estudianteId);
    }

    public List<matricula> obtenerPorCurso(Long cursoId) {
        return matriculaRepository.findByCursoId(cursoId);
    }

    public Optional<matricula> crear(Long estudianteId, Long cursoId,
                                     String fecha, String estado) {
        Optional<estudiante> est = estudianteRepository.findById(estudianteId);
        Optional<curso> cur = cursoRepository.findById(cursoId);
        if (est.isEmpty() || cur.isEmpty()) return Optional.empty();

        matricula m = new matricula(fecha, estado, est.get(), cur.get());
        return Optional.of(matriculaRepository.save(m));
    }

    public boolean eliminar(Long id) {
        if (matriculaRepository.existsById(id)) {
            matriculaRepository.deleteById(id);
            return true;
        }
        return false;
    }




}