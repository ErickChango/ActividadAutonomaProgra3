// CarnetController.java
package com.itsqmet.matu_relaciones.controller;

import com.itsqmet.matu_relaciones.service.CarnetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/carnets")
@CrossOrigin("*")
public class CarnetController {

    @Autowired
    private CarnetService carnetService;

    @GetMapping
    public ResponseEntity<?> obtenerTodos() {
        return ResponseEntity.ok(carnetService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Long id) {
        return carnetService.obtenerPorId(id)
                .map(c -> ResponseEntity.ok((Object) c))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error", "Carnet no encontrado")));
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Map<String, String> datos) {
        Long estudianteId = Long.parseLong(datos.get("estudianteId"));
        String numero = datos.get("numero");
        String anioVigencia = datos.get("anioVigencia");

        return carnetService.crear(estudianteId, numero, anioVigencia)
                .map(c -> ResponseEntity.status(HttpStatus.CREATED).body((Object) c))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error", "Estudiante no encontrado")));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        if (carnetService.eliminar(id)) {
            return ResponseEntity.ok(Map.of("mensaje", "Carnet eliminado"));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("error", "Carnet no encontrado"));
    }
}