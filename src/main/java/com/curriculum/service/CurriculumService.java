package com.curriculum.service;

import com.curriculum.model.Persona;
import com.curriculum.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CurriculumService {

    @Autowired
    private PersonaRepository personaRepository;

    public Persona obtenerPersona(Long id) {
        return personaRepository.findById(id).orElseGet(() -> {
            Persona nueva = new Persona();
            nueva.setNombre("Tu Nombre");
            nueva.setApellido("Tu Apellido");
            nueva.setEmail("email@ejemplo.com");
            nueva.setTelefono("+57 300 000 0000");
            nueva.setCiudad("Bogotá");
            nueva.setPais("Colombia");
            nueva.setPerfil("Desarrollador apasionado por crear soluciones tecnológicas innovadoras.");
            nueva.setExperiencia("Empresa XYZ - Desarrollador Backend (2022 - Presente)\nDesarrollo de APIs REST con Spring Boot y PostgreSQL.\n\nEmpresa ABC - Desarrollador Junior (2020 - 2022)\nMantenimiento y desarrollo de aplicaciones web.");
            nueva.setEducacion("Universidad Nacional - Ingeniería de Sistemas (2016 - 2021)\nGraduado con honores.\n\nCertificación AWS Cloud Practitioner (2023)");
            nueva.setHabilidades("Java, Spring Boot, PostgreSQL, React, Docker, Git, AWS");
            nueva.setLinkedin("https://linkedin.com/in/tuperfil");
            nueva.setGithub("https://github.com/tuusuario");
            return personaRepository.save(nueva);
        });
    }

    public Persona guardarPersona(Persona persona) {
        return personaRepository.save(persona);
    }
}
