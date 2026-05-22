package com.quiz.app.controladores;


import com.quiz.app.models.Entrenador;
import com.quiz.app.repository.EntrenadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/entrenadores")
public class EntrenadorController {

    @Autowired private EntrenadorRepository entrenadorRepo;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("entrenadores", entrenadorRepo.findAll());
        model.addAttribute("titulo", "Listado de Entrenadores");
        return "entrenadores/listar";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("entrenador", new Entrenador());
        model.addAttribute("titulo", "Nuevo Entrenador");
        return "entrenadores/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Entrenador entrenador, RedirectAttributes ra) {
        entrenadorRepo.save(entrenador);
        ra.addFlashAttribute("exito", "Entrenador guardado correctamente.");
        return "redirect:/entrenadores";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model, RedirectAttributes ra) {
        Entrenador e = entrenadorRepo.findById(id).orElse(null);
        if (e == null) { ra.addFlashAttribute("error", "Entrenador no encontrado."); return "redirect:/entrenadores"; }
        model.addAttribute("entrenador", e);
        model.addAttribute("titulo", "Editar Entrenador");
        return "entrenadores/formulario";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, RedirectAttributes ra) {
        if (!entrenadorRepo.existsById(id)) { ra.addFlashAttribute("error", "Entrenador no encontrado."); return "redirect:/entrenadores"; }
        entrenadorRepo.deleteById(id);
        ra.addFlashAttribute("exito", "Entrenador eliminado correctamente.");
        return "redirect:/entrenadores";
    }
}
