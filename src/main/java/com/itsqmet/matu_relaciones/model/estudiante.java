package com.itsqmet.matu_relaciones.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
//import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "estudiantes")
public class estudiante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    @Column(nullable = false)
    private String nombre;

    @NotBlank(message = "El apellido es obligatorio")
    private String apellido;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email no tiene formato válido")
    @Column(unique = true, nullable = false)
    private String email;



    @OneToOne(mappedBy = "estudiante", cascade = CascadeType.ALL)
    @JsonManagedReference("estudiante-carnet")
    private carnet carnet;


    @OneToMany(mappedBy = "estudiante")
    @JsonManagedReference("estudiante-matriculas")
    private List<matricula> matriculas;


    @ManyToMany
    @JoinTable(
            name = "estudiante_curso",
            joinColumns = @JoinColumn(name = "estudiante_id"),
            inverseJoinColumns = @JoinColumn(name = "curso_id")
    )
    private List<curso> cursos = new ArrayList<>();








    public estudiante() {}

    public estudiante(String nombre, String apellido, String email) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public carnet getCarnet() { return carnet; }
    public void setCarnet(carnet carnet) { this.carnet = carnet; }
    public List<matricula> getMatriculas() { return matriculas; }
    public void setMatriculas(List<matricula> matriculas) { this.matriculas = matriculas; }
    public List<curso> getCursos() { return cursos; }
    public void setCursos(List<curso> cursos) { this.cursos = cursos; }
}