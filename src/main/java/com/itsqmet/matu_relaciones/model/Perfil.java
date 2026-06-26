package com.itsqmet.matu_relaciones.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "perfiles")
public class Perfil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "La biografia es obligatoria")
    @Column(columnDefinition = "TEXT")
    private String biografia;

    @NotBlank(message = "La nacionalidad es obligatoria")
    private String nacionalidad;

    private String sitioWeb;

    // relacion uno a uno con Autor (lado propietario)
    @OneToOne
    @JoinColumn(name = "autor_id", nullable = false)
    @JsonBackReference("autor-perfil")
    private Autor autor;

    public Perfil() {}

    public Perfil(String biografia, String nacionalidad, String sitioWeb, Autor autor) {
        this.biografia = biografia;
        this.nacionalidad = nacionalidad;
        this.sitioWeb = sitioWeb;
        this.autor = autor;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getBiografia() { return biografia; }
    public void setBiografia(String biografia) { this.biografia = biografia; }
    public String getNacionalidad() { return nacionalidad; }
    public void setNacionalidad(String nacionalidad) { this.nacionalidad = nacionalidad; }
    public String getSitioWeb() { return sitioWeb; }
    public void setSitioWeb(String sitioWeb) { this.sitioWeb = sitioWeb; }
    public Autor getAutor() { return autor; }
    public void setAutor(Autor autor) { this.autor = autor; }
}
