package raphael.tontinepro.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import raphael.tontinepro.repository.TontineRepository;

@Controller
public class DashboardController {

    private final TontineRepository tontineRepository;

    public DashboardController(TontineRepository tontineRepository) {
        this.tontineRepository = tontineRepository;
    }

    @GetMapping("/dashboard")
    public String afficherDashboard(Model model, @AuthenticationPrincipal OAuth2User principal) {
        if (principal != null) {
            // Récupération des infos Google
            model.addAttribute("userName", principal.getAttribute("name"));
            model.addAttribute("userEmail", principal.getAttribute("email"));
            model.addAttribute("userPicture", principal.getAttribute("picture"));
        }

        // Données d'aperçu pour le dashboard
        model.addAttribute("totalTontines", tontineRepository.count());
        return "dashboard";
    }
}