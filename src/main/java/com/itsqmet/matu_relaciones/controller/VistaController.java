package com.itsqmet.matu_relaciones.controller;

import com.itsqmet.matu_relaciones.model.Categoria;
import com.itsqmet.matu_relaciones.service.AutorService;
import com.itsqmet.matu_relaciones.service.CategoriaService;
import com.itsqmet.matu_relaciones.service.LibroService;
import com.itsqmet.matu_relaciones.service.PerfilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class VistaController {

    @Autowired private AutorService autorService;
    @Autowired private PerfilService perfilService;
    @Autowired private LibroService libroService;
    @Autowired private CategoriaService categoriaService;

    // ── Página principal ──────────────────────────────────────────────────────
    @GetMapping("/")
    public String inicio() {
        return "redirect:/autores";
    }

    // ── Autores ───────────────────────────────────────────────────────────────
    @GetMapping("/autores")
    public String autores(Model model) {
        model.addAttribute("autores", autorService.obtenerTodos());
        return "autores/lista";
    }

    // ── Perfiles ──────────────────────────────────────────────────────────────
    @GetMapping("/perfiles")
    public String perfiles(Model model) {
        model.addAttribute("perfiles", perfilService.obtenerTodos());
        model.addAttribute("autores", autorService.obtenerTodos());
        return "perfiles/lista";
    }

    // ── Libros ────────────────────────────────────────────────────────────────
    @GetMapping("/libros")
    public String libros(Model model) {
        model.addAttribute("libros", libroService.obtenerTodos());
        model.addAttribute("autores", autorService.obtenerTodos());
        model.addAttribute("categorias", categoriaService.obtenerTodas());
        return "libros/lista";
    }

    // ── Categorías ────────────────────────────────────────────────────────────
    @GetMapping("/categorias")
    public String categorias(Model model) {
        model.addAttribute("categorias", categoriaService.obtenerTodas());
        return "categorias/lista";
    }
}
