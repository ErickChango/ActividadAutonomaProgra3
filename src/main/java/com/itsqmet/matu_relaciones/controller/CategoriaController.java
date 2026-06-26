package com.itsqmet.matu_relaciones.controller;

import com.itsqmet.matu_relaciones.model.Categoria;
import com.itsqmet.matu_relaciones.service.CategoriaService;
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
@RequestMapping("/api/categorias")
@CrossOrigin("*")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public ResponseEntity<List<Categoria>> obtenerTodas() {
        return ResponseEntity.ok(categoriaService.obtenerTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Long id) {
        return categoriaService.obtenerPorId(id)
                .map(c -> ResponseEntity.ok((Object) c))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error", "Categoría no encontrada")));
    }

    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody Categoria categoria,
                                   BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errores = new HashMap<>();
            result.getFieldErrors().forEach(e ->
                    errores.put(e.getField(), e.getDefaultMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errores);
        }
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(categoriaService.crear(categoria));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id,
                                        @Valid @RequestBody Categoria categoria,
                                        BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errores = new HashMap<>();
            result.getFieldErrors().forEach(e ->
                    errores.put(e.getField(), e.getDefaultMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errores);
        }
        return categoriaService.actualizar(id, categoria)
                .map(c -> ResponseEntity.ok((Object) c))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error", "Categoría no encontrada")));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        if (categoriaService.eliminar(id)) {
            return ResponseEntity.ok(Map.of("mensaje", "Categoría eliminada"));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("error", "Categoría no encontrada"));
    }
}
