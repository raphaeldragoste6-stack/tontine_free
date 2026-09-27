package raphael.tontinepro.controller;

import raphael.tontinepro.repository.TontineRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    private final TontineRepository tontineRepository;

    public DashboardController(TontineRepository tontineRepository) {
        this.tontineRepository = tontineRepository;
    }

    @GetMapping("/dashboard")
    public String dashboard(@AuthenticationPrincipal OAuth2User principal, Model model) {
        // 1. Informations de l'utilisateur Google
        if (principal != null) {
            model.addAttribute("userName", principal.getAttribute("name"));
            model.addAttribute("userEmail", principal.getAttribute("email"));
            model.addAttribute("userPicture", principal.getAttribute("picture"));
        }

        // 2. Nombre total de tontines via le repository (Spring Data JPA)
        long totalTontines = tontineRepository.count();

        model.addAttribute("totalTontines", totalTontines);
        model.addAttribute("totalCotisations", 0.0);
        model.addAttribute("cotisationsEnRetard", 0);
        model.addAttribute("prochainBeneficiaire", "Aucun");
        model.addAttribute("dateProchainTirage", null);

        return "dashboard";
    }
}