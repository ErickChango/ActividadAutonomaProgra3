package com.itsqmet.matu_relaciones.service;

import com.itsqmet.matu_relaciones.model.Autor;
import com.itsqmet.matu_relaciones.model.Perfil;
import com.itsqmet.matu_relaciones.repository.AutorRepository;
import com.itsqmet.matu_relaciones.repository.PerfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PerfilService {

    @Autowired
    private PerfilRepository perfilRepository;

    @Autowired
    private AutorRepository autorRepository;

    public List<Perfil> obtenerTodos() {
        return perfilRepository.findAll();
    }

    public Optional<Perfil> obtenerPorId(Long id) {
        return perfilRepository.findById(id);
    }

    public Optional<Perfil> crear(Long autorId, String biografia,
                                   String nacionalidad, String sitioWeb) {
        Optional<Autor> autor = autorRepository.findById(autorId);
        if (autor.isEmpty()) return Optional.empty();

        Perfil perfil = new Perfil(biografia, nacionalidad, sitioWeb, autor.get());
        return Optional.of(perfilRepository.save(perfil));
    }

    public Optional<Perfil> actualizar(Long id, String biografia,
                                        String nacionalidad, String sitioWeb) {
        return perfilRepository.findById(id).map(p -> {
            p.setBiografia(biografia);
            p.setNacionalidad(nacionalidad);
            p.setSitioWeb(sitioWeb);
            return perfilRepository.save(p);
        });
    }

    public boolean eliminar(Long id) {
        if (perfilRepository.existsById(id)) {
            perfilRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
