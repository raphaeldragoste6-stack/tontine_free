package raphael.tontinepro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccueilViewController {

    @GetMapping("/")
    public String accueil() {
        return "index"; // Renvoie vers index.html
    }
}