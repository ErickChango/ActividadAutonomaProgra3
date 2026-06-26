package com.itsqmet.matu_relaciones.service;

import com.itsqmet.matu_relaciones.model.Categoria;
import com.itsqmet.matu_relaciones.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Categoria> obtenerTodas() {
        return categoriaRepository.findAll();
    }

    public Optional<Categoria> obtenerPorId(Long id) {
        return categoriaRepository.findById(id);
    }

    public Categoria crear(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public Optional<Categoria> actualizar(Long id, Categoria datos) {
        return categoriaRepository.findById(id).map(c -> {
            c.setNombre(datos.getNombre());
            c.setDescripcion(datos.getDescripcion());
            return categoriaRepository.save(c);
        });
    }

    public boolean eliminar(Long id) {
        if (categoriaRepository.existsById(id)) {
            categoriaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
