package com.quiz.app.controladores;

import com.quiz.app.models.Club;
import com.quiz.app.models.Competicion;
import com.quiz.app.models.Jugador; // ← AGREGAR ESTA IMPORTACIÓN
import com.quiz.app.repository.AsociacionRepository;
import com.quiz.app.repository.ClubRepository;
import com.quiz.app.repository.CompeticionRepository;
import com.quiz.app.repository.EntrenadorRepository;
import com.quiz.app.repository.JugadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/clubes")
public class ClubController {

    @Autowired private ClubRepository clubRepo;
    @Autowired private AsociacionRepository asociacionRepo;
    @Autowired private EntrenadorRepository entrenadorRepo;
    @Autowired private CompeticionRepository competicionRepo;
    @Autowired private JugadorRepository jugadorRepo;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("clubes", clubRepo.findAll());
        model.addAttribute("titulo", "Listado de Clubes");
        return "clubes/listar";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("club", new Club());
        model.addAttribute("asociaciones", asociacionRepo.findAll());
        model.addAttribute("entrenadores", entrenadorRepo.findAll());
        model.addAttribute("competiciones", competicionRepo.findAll());
        model.addAttribute("jugadores", jugadorRepo.findAll());
        model.addAttribute("titulo", "Nuevo Club");
        return "clubes/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Club club,
                          @RequestParam(required = false) List<Long> competicionIds,
                          @RequestParam(required = false) List<Long> jugadorIds,
                          RedirectAttributes ra) {
        
        // Guardar competiciones
        if (competicionIds != null) {
            List<Competicion> comps = competicionIds.stream()
                .map(id -> competicionRepo.findById(id).orElse(null))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
            club.setCompeticiones(comps);
        }
        
        // Guardar jugadores - IMPORTANTE: usar el setter correcto para @OneToMany
        if (jugadorIds != null) {
            List<Jugador> jugadores = jugadorIds.stream()
                .map(id -> jugadorRepo.findById(id).orElse(null))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
            club.setJugadores(jugadores);
        }
        
        clubRepo.save(club);
        ra.addFlashAttribute("exito", "Club guardado correctamente.");
        return "redirect:/clubes";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model, RedirectAttributes ra) {
        Club club = clubRepo.findById(id).orElse(null);
        if (club == null) { 
            ra.addFlashAttribute("error", "Club no encontrado."); 
            return "redirect:/clubes"; 
        }
        model.addAttribute("club", club);
        model.addAttribute("asociaciones", asociacionRepo.findAll());
        model.addAttribute("entrenadores", entrenadorRepo.findAll());
        model.addAttribute("competiciones", competicionRepo.findAll());
        model.addAttribute("jugadores", jugadorRepo.findAll());
        model.addAttribute("titulo", "Editar Club");
        return "clubes/formulario";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, RedirectAttributes ra) {
        if (!clubRepo.existsById(id)) { 
            ra.addFlashAttribute("error", "Club no encontrado."); 
            return "redirect:/clubes"; 
        }
        clubRepo.deleteById(id);
        ra.addFlashAttribute("exito", "Club eliminado correctamente.");
        return "redirect:/clubes";
    }

    @GetMapping("/detalle/{id}")
    public String detalle(@PathVariable Long id, Model model, RedirectAttributes ra) {
        Club club = clubRepo.findById(id).orElse(null);
        if (club == null) { 
            ra.addFlashAttribute("error", "Club no encontrado."); 
            return "redirect:/clubes"; 
        }
        model.addAttribute("club", club);
        model.addAttribute("titulo", "Detalle del Club");
        return "clubes/detalle";
    }
}