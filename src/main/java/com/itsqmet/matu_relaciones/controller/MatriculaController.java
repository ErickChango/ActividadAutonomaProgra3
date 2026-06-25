package com.itsqmet.matu_relaciones.controller;

import com.itsqmet.matu_relaciones.service.MatriculaService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/matriculas")
@CrossOrigin("*")
public class MatriculaController {

    @Autowired
    private MatriculaService matriculaService;

    @GetMapping
    public ResponseEntity<?> obtenerTodas() {
        return ResponseEntity.ok(matriculaService.obtenerTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Long id) {
        return matriculaService.obtenerPorId(id)
                .map(m -> ResponseEntity.ok((Object) m))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error", "Matrícula no encontrada")));
    }

    @GetMapping("/estudiante/{estudianteId}")
    public ResponseEntity<?> porEstudiante(@PathVariable Long estudianteId) {
        return ResponseEntity.ok(
                matriculaService.obtenerPorEstudiante(estudianteId));
    }

    @GetMapping("/curso/{cursoId}")
    public ResponseEntity<?> porCurso(@PathVariable Long cursoId) {
        return ResponseEntity.ok(
                matriculaService.obtenerPorCurso(cursoId));
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Map<String, String> datos) {
        Long estudianteId = Long.parseLong(datos.get("estudianteId"));
        Long cursoId = Long.parseLong(datos.get("cursoId"));
        String fecha = datos.get("fecha");
        String estado = datos.get("estado");
        return matriculaService.crear(estudianteId, cursoId, fecha, estado)
                .map(m -> ResponseEntity.status(HttpStatus.CREATED).body((Object) m))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error", "Estudiante o curso no existe")));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        if (matriculaService.eliminar(id)) {
            return ResponseEntity.ok(Map.of("mensaje", "Matrícula eliminada"));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("error", "Matrícula no encontrada"));
    }



}