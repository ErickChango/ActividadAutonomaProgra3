package com.itsqmet.matu_relaciones.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "matriculas")
public class matricula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "La fecha de matrícula es obligatoria")
    private String fechaMatricula;

    @NotBlank(message = "El estado es obligatorio")
    private String estado;

    @ManyToOne
    @JoinColumn(name = "estudiante_id", nullable = false)
    @JsonBackReference("estudiante-matriculas")
    private estudiante estudiante;


    @ManyToOne
    @JoinColumn(name = "curso_id", nullable = false)
    @JsonBackReference("curso-matriculas")
    private curso curso;

    public matricula() {}

    public matricula(String fechaMatricula, String estado,
                     estudiante estudiante, curso curso) {
        this.fechaMatricula = fechaMatricula;
        this.estado = estado;
        this.estudiante = estudiante;
        this.curso = curso;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getFechaMatricula() { return fechaMatricula; }
    public void setFechaMatricula(String fechaMatricula) { this.fechaMatricula = fechaMatricula; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public estudiante getEstudiante() { return estudiante; }
    public void setEstudiante(estudiante estudiante) { this.estudiante = estudiante; }
    public curso getCurso() { return curso; }
    public void setCurso(curso curso) { this.curso = curso; }
}