package com.quiz.app.controladores;

import com.quiz.app.models.Asociacion;
import com.quiz.app.repository.AsociacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/asociaciones")
public class AsociacionController {

    @Autowired private AsociacionRepository asociacionRepo;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("asociaciones", asociacionRepo.findAll());
        model.addAttribute("titulo", "Listado de Asociaciones");
        return "asociaciones/listar";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("asociacion", new Asociacion());
        model.addAttribute("titulo", "Nueva Asociación");
        return "asociaciones/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Asociacion asociacion, RedirectAttributes ra) {
        asociacionRepo.save(asociacion);
        ra.addFlashAttribute("exito", "Asociación guardada correctamente.");
        return "redirect:/asociaciones";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model, RedirectAttributes ra) {
        Asociacion a = asociacionRepo.findById(id).orElse(null);
        if (a == null) { ra.addFlashAttribute("error", "Asociación no encontrada."); return "redirect:/asociaciones"; }
        model.addAttribute("asociacion", a);
        model.addAttribute("titulo", "Editar Asociación");
        return "asociaciones/formulario";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, RedirectAttributes ra) {
        if (!asociacionRepo.existsById(id)) { ra.addFlashAttribute("error", "Asociación no encontrada."); return "redirect:/asociaciones"; }
        asociacionRepo.deleteById(id);
        ra.addFlashAttribute("exito", "Asociación eliminada correctamente.");
        return "redirect:/asociaciones";
    }
}

