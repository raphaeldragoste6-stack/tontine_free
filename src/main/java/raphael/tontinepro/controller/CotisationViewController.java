package raphael.tontinepro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import raphael.tontinepro.dto.CotisationDTO;
import raphael.tontinepro.service.CotisationService;
import raphael.tontinepro.service.MembreService;
import raphael.tontinepro.service.TontineService;

@Controller
@RequestMapping("/cotisations")
public class CotisationViewController {

    private final CotisationService cotisationService;
    private final MembreService membreService;
    private final TontineService tontineService;

    public CotisationViewController(CotisationService cotisationService, MembreService membreService, TontineService tontineService) {
        this.cotisationService = cotisationService;
        this.membreService = membreService;
        this.tontineService = tontineService;
    }

    @GetMapping
    public String listerCotisations(Model model) {
        model.addAttribute("cotisations", cotisationService.listerToutesLesCotisations());
        model.addAttribute("tontines", tontineService.listerToutesLesTontines());
        model.addAttribute("membres", membreService.listerTousLesMembres());
        model.addAttribute("nouvelleCotisation", new CotisationDTO());
        return "cotisations";
    }

    @PostMapping("/creer")
    public String enregistrerCotisation(@ModelAttribute("nouvelleCotisation") CotisationDTO cotisationDTO) {
        cotisationService.enregistrerCotisation(cotisationDTO);
        return "redirect:/cotisations";
    }
}