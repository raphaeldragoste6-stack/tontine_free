package raphael.tontinepro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import raphael.tontinepro.dto.TontineDTO;
import raphael.tontinepro.service.TontineService;

@Controller
@RequestMapping("/tontines")
public class TontineViewController {

    private final TontineService tontineService;

    public TontineViewController(TontineService tontineService) {
        this.tontineService = tontineService;
    }

    @GetMapping
    public String listerTontines(Model model) {
        model.addAttribute("tontines", tontineService.listerToutesLesTontines());


        model.addAttribute("nouvelleTontine", new TontineDTO());
        return "tontines";
    }

    @PostMapping("/creer")
    public String enregistrerTontine(@ModelAttribute("nouvelleTontine") TontineDTO tontineDTO) {
        tontineService.creerTontine(tontineDTO);
        return "redirect:/tontines";
    }
}