package raphael.tontinepro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccueilViewController {

    @GetMapping("/")
    public String afficherAccueil() {
        return "index"; // Va chercher le fichier src/main/resources/templates/index.html
    }
}