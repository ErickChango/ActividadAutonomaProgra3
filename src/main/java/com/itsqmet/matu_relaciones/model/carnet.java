package com.itsqmet.matu_relaciones.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "carnets")
public class carnet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El número de carnet es obligatorio")
    @Column(unique = true, nullable = false)
    private String numero;

    private String anioVigencia;

    @OneToOne
    @JoinColumn(name = "estudiante_id", nullable = false)
    @JsonBackReference("estudiante-carnet")
    private estudiante estudiante;

    public carnet() {}

    public carnet(String numero, String anioVigencia, estudiante estudiante) {
        this.numero = numero;
        this.anioVigencia = anioVigencia;
        this.estudiante = estudiante;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }
    public String getAnioVigencia() { return anioVigencia; }
    public void setAnioVigencia(String anioVigencia) { this.anioVigencia = anioVigencia; }
    public estudiante getEstudiante() { return estudiante; }
    public void setEstudiante(estudiante estudiante) { this.estudiante = estudiante; }
}