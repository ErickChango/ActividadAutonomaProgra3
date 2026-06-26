package com.itsqmet.matu_relaciones.service;

import com.itsqmet.matu_relaciones.model.Autor;
import com.itsqmet.matu_relaciones.repository.AutorRepository;
import com.itsqmet.matu_relaciones.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class AutorService {

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private LibroRepository libroRepository;

    public List<Autor> obtenerTodos() {
        return autorRepository.findAll();
    }

    public Optional<Autor> obtenerPorId(Long id) {
        return autorRepository.findById(id);
    }

    public Autor crear(Autor autor) {
        return autorRepository.save(autor);
    }

    public Optional<Autor> actualizar(Long id, Autor datos) {
        return autorRepository.findById(id).map(a -> {
            a.setNombre(datos.getNombre());
            a.setApellido(datos.getApellido());
            a.setEmail(datos.getEmail());
            return autorRepository.save(a);
        });
    }

    @Transactional
    public boolean eliminar(Long id) {
        return autorRepository.findById(id).map(autor -> {
            // Primero limpiar las categorias de cada libro del autor
            // para evitar constraint violation en la tabla libro_categoria
            autor.getLibros().forEach(libro -> libro.getCategorias().clear());
            libroRepository.saveAll(autor.getLibros());
            autorRepository.delete(autor);
            return true;
        }).orElse(false);
    }
}
