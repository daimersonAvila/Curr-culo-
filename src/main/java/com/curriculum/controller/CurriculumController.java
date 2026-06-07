package com.curriculum.controller;

import com.curriculum.model.Persona;
import com.curriculum.service.CurriculumService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CurriculumController {

    @Autowired
    private CurriculumService curriculumService;

    // CV público - acceso libre
    @GetMapping("/")
    public String index(Model model) {
        Persona persona = curriculumService.obtenerPersona(1L);
        model.addAttribute("persona", persona);
        return "index";
    }

    // Login page
    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "logout", required = false) String logout,
                        Model model) {
        if (error != null) {
            model.addAttribute("errorMsg", "Usuario o contraseña incorrectos.");
        }
        if (logout != null) {
            model.addAttribute("logoutMsg", "Sesión cerrada correctamente.");
        }
        return "login";
    }

    // Formulario de edición - protegido (solo ADMIN)
    @GetMapping("/editar")
    public String mostrarFormularioEdicion(Model model) {
        Persona persona = curriculumService.obtenerPersona(1L);
        model.addAttribute("persona", persona);
        return "formulario";
    }

    // Guardar cambios - protegido (solo ADMIN)
    @PostMapping("/guardar")
    public String guardarPersona(@Valid @ModelAttribute Persona persona,
                                 BindingResult result,
                                 RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "formulario";
        }
        curriculumService.guardarPersona(persona);
        redirectAttributes.addFlashAttribute("mensaje", "¡Información actualizada exitosamente!");
        return "redirect:/";
    }
}
