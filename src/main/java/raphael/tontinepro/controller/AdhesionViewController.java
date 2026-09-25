package raphael.tontinepro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import raphael.tontinepro.dto.AdhesionDTO;
import raphael.tontinepro.service.AdhesionService;
import raphael.tontinepro.service.MembreService;
import raphael.tontinepro.service.TontineService;

@Controller
@RequestMapping("/adhesions")
public class AdhesionViewController {

    private final AdhesionService adhesionService;
    private final MembreService membreService;
    private final TontineService tontineService;

    public AdhesionViewController(AdhesionService adhesionService, MembreService membreService, TontineService tontineService) {
        this.adhesionService = adhesionService;
        this.membreService = membreService;
        this.tontineService = tontineService;
    }

    @GetMapping
    public String listerAdhesions(Model model) {
        model.addAttribute("adhesions", adhesionService.listerToutesLesAdhesions());
        model.addAttribute("membres", membreService.listerTousLesMembres());
        model.addAttribute("tontines", tontineService.listerToutesLesTontines());
        model.addAttribute("nouvelleAdhesion", new AdhesionDTO());
        return "adhesions";
    }

    @PostMapping
    public String enregistrerAdhesion(@ModelAttribute("nouvelleAdhesion") AdhesionDTO dto) {
        adhesionService.inscrireMembreATontine(dto);
        return "redirect:/adhesions";
    }
}