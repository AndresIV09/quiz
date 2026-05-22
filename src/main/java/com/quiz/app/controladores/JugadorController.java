package com.quiz.app.controladores;

import com.quiz.app.models.Jugador;
import com.quiz.app.repository.JugadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/jugadores")
public class JugadorController {

    @Autowired private JugadorRepository jugadorRepo;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("jugadores", jugadorRepo.findAll());
        model.addAttribute("titulo", "Listado de Jugadores");
        return "jugadores/listar";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("jugador", new Jugador());
        model.addAttribute("titulo", "Nuevo Jugador");
        return "jugadores/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Jugador jugador, RedirectAttributes ra) {
        jugadorRepo.save(jugador);
        ra.addFlashAttribute("exito", "Jugador guardado correctamente.");
        return "redirect:/jugadores";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model, RedirectAttributes ra) {
        Jugador j = jugadorRepo.findById(id).orElse(null);
        if (j == null) { ra.addFlashAttribute("error", "Jugador no encontrado."); return "redirect:/jugadores"; }
        model.addAttribute("jugador", j);
        model.addAttribute("titulo", "Editar Jugador");
        return "jugadores/formulario";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, RedirectAttributes ra) {
        if (!jugadorRepo.existsById(id)) { ra.addFlashAttribute("error", "Jugador no encontrado."); return "redirect:/jugadores"; }
        jugadorRepo.deleteById(id);
        ra.addFlashAttribute("exito", "Jugador eliminado correctamente.");
        return "redirect:/jugadores";
    }
}
