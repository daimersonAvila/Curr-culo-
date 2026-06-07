package com.curriculum.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "personas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    private String ciudad;
    private String pais;

    @Column(length = 2000)
    private String perfil;

    @Column(length = 5000)
    private String experiencia;

    @Column(length = 5000)
    private String educacion;

    @Column(length = 2000)
    private String habilidades;

    private String linkedin;
    private String github;
}
