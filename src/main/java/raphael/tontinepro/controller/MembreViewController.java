package raphael.tontinepro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import raphael.tontinepro.dto.MembreDTO;
import raphael.tontinepro.service.MembreService;

@Controller
@RequestMapping("/membres")
public class MembreViewController {

    private final MembreService membreService;

    public MembreViewController(MembreService membreService) {
        this.membreService = membreService;
    }

    @GetMapping
    public String listerMembres(Model model) {
        model.addAttribute("membres", membreService.listerTousLesMembres());
        model.addAttribute("nouveauMembre", new MembreDTO());
        return "membres";
    }

    @PostMapping("/creer")
    public String enregistrerMembre(@ModelAttribute("nouveauMembre") MembreDTO membreDTO) {
        membreService.creerMembre(membreDTO);
        return "redirect:/membres";
    }
}