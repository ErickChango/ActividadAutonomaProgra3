package com.itsqmet.matu_relaciones.service;

import com.itsqmet.matu_relaciones.model.Autor;
import com.itsqmet.matu_relaciones.model.Categoria;
import com.itsqmet.matu_relaciones.model.Libro;
import com.itsqmet.matu_relaciones.repository.AutorRepository;
import com.itsqmet.matu_relaciones.repository.CategoriaRepository;
import com.itsqmet.matu_relaciones.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class LibroService {

    @Autowired
    private LibroRepository libroRepository;

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Libro> obtenerTodos() {
        return libroRepository.findAll();
    }

    public Optional<Libro> obtenerPorId(Long id) {
        return libroRepository.findById(id);
    }

    public List<Libro> obtenerPorAutor(Long autorId) {
        return libroRepository.findByAutorId(autorId);
    }

    public Optional<Libro> crear(Long autorId, String titulo, String isbn,
                                  Integer anioPublicacion, String editorial) {
        Optional<Autor> autor = autorRepository.findById(autorId);
        if (autor.isEmpty()) return Optional.empty();

        Libro libro = new Libro(titulo, isbn, anioPublicacion, editorial, autor.get());
        return Optional.of(libroRepository.save(libro));
    }

    public Optional<Libro> actualizar(Long id, Long autorId, String titulo, String isbn,
                                       Integer anioPublicacion, String editorial) {
        Optional<Libro> libroOpt = libroRepository.findById(id);
        if (libroOpt.isEmpty()) return Optional.empty();

        Optional<Autor> autorOpt = autorRepository.findById(autorId);
        if (autorOpt.isEmpty()) return Optional.empty();

        Libro l = libroOpt.get();
        l.setTitulo(titulo);
        l.setIsbn(isbn);
        l.setAnioPublicacion(anioPublicacion);
        l.setEditorial(editorial);
        l.setAutor(autorOpt.get());
        return Optional.of(libroRepository.save(l));
    }

    public boolean eliminar(Long id) {
        if (libroRepository.existsById(id)) {
            libroRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Optional<Libro> agregarCategoria(Long libroId, Long categoriaId) {
        Optional<Libro> libro = libroRepository.findById(libroId);
        Optional<Categoria> categoria = categoriaRepository.findById(categoriaId);
        if (libro.isEmpty() || categoria.isEmpty()) return Optional.empty();

        libro.get().getCategorias().add(categoria.get());
        return Optional.of(libroRepository.save(libro.get()));
    }

    public Optional<Libro> quitarCategoria(Long libroId, Long categoriaId) {
        Optional<Libro> libro = libroRepository.findById(libroId);
        Optional<Categoria> categoria = categoriaRepository.findById(categoriaId);
        if (libro.isEmpty() || categoria.isEmpty()) return Optional.empty();

        libro.get().getCategorias().removeIf(c -> c.getId().equals(categoriaId));
        return Optional.of(libroRepository.save(libro.get()));
    }
}
