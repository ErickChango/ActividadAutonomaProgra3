package com.itsqmet.matu_relaciones.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "cursos")
public class curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre del curso es obligatorio")
    @Column(nullable = false)
    private String nombre;

    @NotBlank(message = "La descripción es obligatoria")
    private String descripcion;

    @NotNull(message = "El nivel es obligatorio")
    private Integer nivel;

    @OneToMany(mappedBy = "curso")
    @JsonManagedReference("curso-matriculas")
    private List<matricula> matriculas;

    @ManyToMany(mappedBy = "cursos")
    @JsonIgnore
    private List<estudiante> estudiantes;




    public curso() {}

    public curso(String nombre, String descripcion, Integer nivel) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.nivel = nivel;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public Integer getNivel() { return nivel; }
    public void setNivel(Integer nivel) { this.nivel = nivel; }
    public List<matricula> getMatriculas() { return matriculas; }
    public void setMatriculas(List<matricula> matriculas) { this.matriculas = matriculas; }
    public List<estudiante> getEstudiantes() { return estudiantes; }
    public void setEstudiantes(List<estudiante> estudiantes) { this.estudiantes = estudiantes; }
}