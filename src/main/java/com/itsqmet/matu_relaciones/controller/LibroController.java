package com.itsqmet.matu_relaciones.controller;

import com.itsqmet.matu_relaciones.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/libros")
@CrossOrigin("*")
public class LibroController {

    @Autowired
    private LibroService libroService;

    @GetMapping
    public ResponseEntity<?> obtenerTodos() {
        return ResponseEntity.ok(libroService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Long id) {
        return libroService.obtenerPorId(id)
                .map(l -> ResponseEntity.ok((Object) l))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error", "Libro no encontrado")));
    }

    @GetMapping("/autor/{autorId}")
    public ResponseEntity<?> obtenerPorAutor(@PathVariable Long autorId) {
        return ResponseEntity.ok(libroService.obtenerPorAutor(autorId));
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Map<String, String> datos) {
        Long autorId = Long.parseLong(datos.get("autorId"));
        String titulo = datos.get("titulo");
        String isbn = datos.get("isbn");
        Integer anio = Integer.parseInt(datos.get("anioPublicacion"));
        String editorial = datos.getOrDefault("editorial", "");

        return libroService.crear(autorId, titulo, isbn, anio, editorial)
                .map(l -> ResponseEntity.status(HttpStatus.CREATED).body((Object) l))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error", "Autor no encontrado")));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id,
                                        @RequestBody Map<String, String> datos) {
        if (!datos.containsKey("autorId") || datos.get("autorId") == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", "El campo autorId es obligatorio"));
        }
        Long autorId = Long.parseLong(datos.get("autorId"));
        String titulo = datos.get("titulo");
        String isbn = datos.get("isbn");
        Integer anio = Integer.parseInt(datos.get("anioPublicacion"));
        String editorial = datos.getOrDefault("editorial", "");

        return libroService.actualizar(id, autorId, titulo, isbn, anio, editorial)
                .map(l -> ResponseEntity.ok((Object) l))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error", "Libro o autor no encontrado")));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        if (libroService.eliminar(id)) {
            return ResponseEntity.ok(Map.of("mensaje", "Libro eliminado"));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("error", "Libro no encontrado"));
    }

    // Gestión de la relación N:M Libro-Categoría
    @PostMapping("/{libroId}/categorias/{categoriaId}")
    public ResponseEntity<?> agregarCategoria(@PathVariable Long libroId,
                                               @PathVariable Long categoriaId) {
        return libroService.agregarCategoria(libroId, categoriaId)
                .map(l -> ResponseEntity.ok((Object) l))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error", "Libro o categoría no encontrado")));
    }

    @DeleteMapping("/{libroId}/categorias/{categoriaId}")
    public ResponseEntity<?> quitarCategoria(@PathVariable Long libroId,
                                              @PathVariable Long categoriaId) {
        return libroService.quitarCategoria(libroId, categoriaId)
                .map(l -> ResponseEntity.ok((Object) l))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error", "Libro o categoría no encontrado")));
    }
}
