package com.quiz.app.controladores;



import com.quiz.app.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired private ClubRepository clubRepo;
    @Autowired private JugadorRepository jugadorRepo;
    @Autowired private EntrenadorRepository entrenadorRepo;
    @Autowired private AsociacionRepository asociacionRepo;
    @Autowired private CompeticionRepository competicionRepo;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("totalClubes",      clubRepo.count());
        model.addAttribute("totalJugadores",   jugadorRepo.count());
        model.addAttribute("totalEntrenadores",entrenadorRepo.count());
        model.addAttribute("totalAsociaciones",asociacionRepo.count());
        model.addAttribute("totalCompeticiones",competicionRepo.count());
        return "index";
    }
}
