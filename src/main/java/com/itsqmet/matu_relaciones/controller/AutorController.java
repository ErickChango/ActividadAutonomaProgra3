package com.itsqmet.matu_relaciones.controller;

import com.itsqmet.matu_relaciones.model.Autor;
import com.itsqmet.matu_relaciones.service.AutorService;
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
@RequestMapping("/api/autores")
@CrossOrigin("*")
public class AutorController {

    @Autowired
    private AutorService autorService;

    @GetMapping
    public ResponseEntity<List<Autor>> obtenerTodos() {
        return ResponseEntity.ok(autorService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Long id) {
        return autorService.obtenerPorId(id)
                .map(a -> ResponseEntity.ok((Object) a))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error", "Autor no encontrado")));
    }

    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody Autor autor, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errores = new HashMap<>();
            result.getFieldErrors().forEach(e ->
                    errores.put(e.getField(), e.getDefaultMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errores);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(autorService.crear(autor));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id,
                                        @Valid @RequestBody Autor autor,
                                        BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errores = new HashMap<>();
            result.getFieldErrors().forEach(e ->
                    errores.put(e.getField(), e.getDefaultMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errores);
        }
        return autorService.actualizar(id, autor)
                .map(a -> ResponseEntity.ok((Object) a))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error", "Autor no encontrado")));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        if (autorService.eliminar(id)) {
            return ResponseEntity.ok(Map.of("mensaje", "Autor eliminado"));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("error", "Autor no encontrado"));
    }
}
