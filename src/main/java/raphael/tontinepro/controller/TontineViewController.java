package raphael.tontinepro.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import raphael.tontinepro.dto.TontineDTO;
import raphael.tontinepro.service.TontineService;

@Controller
@RequestMapping("/tontines")
@RequiredArgsConstructor
public class TontineViewController {

    private final TontineService tontineService;

    // Affiche la page HTML avec le formulaire et la liste
    @GetMapping
    public String afficherPageTontines(Model model) {
        // C'EST CETTE LIGNE QUI MANQUAIT ET QUI CAUSAIT L'ERREUR 500 !
        model.addAttribute("nouvelleTontine", new TontineDTO());

        // On passe la liste des tontines pour le tableau
        model.addAttribute("tontines", tontineService.listerToutesLesTontines());

        return "tontines"; // Renvoie templates/tontines.html
    }

    // Traite le formulaire de création
    @PostMapping("/sauvegarder")
    public String enregistrerTontine(@Valid @ModelAttribute("nouvelleTontine") TontineDTO dto) {
        tontineService.creerTontine(dto);
        return "redirect:/tontines"; // Recharge la page pour afficher la nouvelle tontine
    }
}