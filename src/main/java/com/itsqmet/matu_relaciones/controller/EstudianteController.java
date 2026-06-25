package com.itsqmet.matu_relaciones.controller;

import com.itsqmet.matu_relaciones.model.estudiante;
import com.itsqmet.matu_relaciones.service.EstudianteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/estudiantes")
@CrossOrigin("*")
public class EstudianteController {

    @Autowired
    private EstudianteService estudianteService;

    @GetMapping
    public ResponseEntity<List<estudiante>> obtenerTodos() {
        return ResponseEntity.ok(estudianteService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Long id) {
        return estudianteService.obtenerPorId(id)
                .map(e -> ResponseEntity.ok((Object) e))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error", "Estudiante no encontrado")));
    }

    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody estudiante estudiante,
                                   BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errores = new HashMap<>();
            result.getFieldErrors().forEach(e ->
                    errores.put(e.getField(), e.getDefaultMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errores);
        }
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(estudianteService.crear(estudiante));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id,
                                        @Valid @RequestBody estudiante estudiante,
                                        BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errores = new HashMap<>();
            result.getFieldErrors().forEach(e ->
                    errores.put(e.getField(), e.getDefaultMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errores);
        }
        return estudianteService.actualizar(id, estudiante)
                .map(e -> ResponseEntity.ok((Object) e))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error", "Estudiante no encontrado")));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        if (estudianteService.eliminar(id)) {
            return ResponseEntity.ok(Map.of("mensaje", "Estudiante eliminado"));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("error", "Estudiante no encontrado"));
    }

    @PostMapping("/{estudianteId}/cursos/{cursoId}")
    public ResponseEntity<?> agregarCurso(@PathVariable Long estudianteId,
                                          @PathVariable Long cursoId) {
        return estudianteService.agregarCurso(estudianteId, cursoId)
                .map(e -> ResponseEntity.ok((Object) e))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error", "Estudiante o curso no encontrado")));
    }

    @DeleteMapping("/{estudianteId}/cursos/{cursoId}")
    public ResponseEntity<?> quitarCurso(@PathVariable Long estudianteId,
                                         @PathVariable Long cursoId) {
        return estudianteService.quitarCurso(estudianteId, cursoId)
                .map(e -> ResponseEntity.ok((Object) e))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error", "Estudiante o curso no encontrado")));
    }
}