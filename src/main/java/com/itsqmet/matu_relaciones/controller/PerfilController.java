package com.itsqmet.matu_relaciones.controller;

import com.itsqmet.matu_relaciones.service.PerfilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/perfiles")
@CrossOrigin("*")
public class PerfilController {

    @Autowired
    private PerfilService perfilService;

    @GetMapping
    public ResponseEntity<?> obtenerTodos() {
        return ResponseEntity.ok(perfilService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Long id) {
        return perfilService.obtenerPorId(id)
                .map(p -> ResponseEntity.ok((Object) p))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error", "Perfil no encontrado")));
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Map<String, String> datos) {
        Long autorId = Long.parseLong(datos.get("autorId"));
        String biografia = datos.get("biografia");
        String nacionalidad = datos.get("nacionalidad");
        String sitioWeb = datos.getOrDefault("sitioWeb", "");

        return perfilService.crear(autorId, biografia, nacionalidad, sitioWeb)
                .map(p -> ResponseEntity.status(HttpStatus.CREATED).body((Object) p))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error", "Autor no encontrado")));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id,
                                        @RequestBody Map<String, String> datos) {
        String biografia = datos.get("biografia");
        String nacionalidad = datos.get("nacionalidad");
        String sitioWeb = datos.getOrDefault("sitioWeb", "");

        return perfilService.actualizar(id, biografia, nacionalidad, sitioWeb)
                .map(p -> ResponseEntity.ok((Object) p))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error", "Perfil no encontrado")));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        if (perfilService.eliminar(id)) {
            return ResponseEntity.ok(Map.of("mensaje", "Perfil eliminado"));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("error", "Perfil no encontrado"));
    }
}
