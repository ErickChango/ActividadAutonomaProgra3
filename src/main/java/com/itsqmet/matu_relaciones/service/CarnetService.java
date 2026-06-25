// CarnetService.java
package com.itsqmet.matu_relaciones.service;

import com.itsqmet.matu_relaciones.model.carnet;
import com.itsqmet.matu_relaciones.model.estudiante;
import com.itsqmet.matu_relaciones.repository.CarnetRepository;
import com.itsqmet.matu_relaciones.repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CarnetService {

    @Autowired
    private CarnetRepository carnetRepository;

    @Autowired
    private EstudianteRepository estudianteRepository;

    public List<carnet> obtenerTodos() {
        return carnetRepository.findAll();
    }

    public Optional<carnet> obtenerPorId(Long id) {
        return carnetRepository.findById(id);
    }

    public Optional<carnet> crear(Long estudianteId, String numero, String anioVigencia) {
        Optional<estudiante> est = estudianteRepository.findById(estudianteId);
        if (est.isEmpty()) return Optional.empty();

        carnet c = new carnet(numero, anioVigencia, est.get());
        return Optional.of(carnetRepository.save(c));
    }

    public boolean eliminar(Long id) {
        if (carnetRepository.existsById(id)) {
            carnetRepository.deleteById(id);
            return true;
        }
        return false;
    }
}