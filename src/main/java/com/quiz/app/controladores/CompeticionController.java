package com.quiz.app.controladores;



import com.quiz.app.models.Competicion;
import com.quiz.app.repository.CompeticionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/competiciones")
public class CompeticionController {

    @Autowired private CompeticionRepository competicionRepo;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("competiciones", competicionRepo.findAll());
        model.addAttribute("titulo", "Listado de Competiciones");
        return "competiciones/listar";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("competicion", new Competicion());
        model.addAttribute("titulo", "Nueva Competición");
        return "competiciones/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Competicion competicion, RedirectAttributes ra) {
        competicionRepo.save(competicion);
        ra.addFlashAttribute("exito", "Competición guardada correctamente.");
        return "redirect:/competiciones";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model, RedirectAttributes ra) {
        Competicion c = competicionRepo.findById(id).orElse(null);
        if (c == null) { ra.addFlashAttribute("error", "Competición no encontrada."); return "redirect:/competiciones"; }
        model.addAttribute("competicion", c);
        model.addAttribute("titulo", "Editar Competición");
        return "competiciones/formulario";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, RedirectAttributes ra) {
        if (!competicionRepo.existsById(id)) { ra.addFlashAttribute("error", "Competición no encontrada."); return "redirect:/competiciones"; }
        competicionRepo.deleteById(id);
        ra.addFlashAttribute("exito", "Competición eliminada correctamente.");
        return "redirect:/competiciones";
    }
}
