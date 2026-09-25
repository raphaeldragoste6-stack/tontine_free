package raphael.tontinepro.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import raphael.tontinepro.dto.CotisationDTO;
import raphael.tontinepro.service.CotisationService;

import java.util.List;

@RestController
@RequestMapping("/api/cotisations")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CotisationController {

    private final CotisationService cotisationService;

    @PostMapping
    public ResponseEntity<CotisationDTO> enregistrerCotisation(@Valid @RequestBody CotisationDTO dto) {
        CotisationDTO nouvelleCotisation = cotisationService.enregistrerCotisation(dto);
        return new ResponseEntity<>(nouvelleCotisation, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CotisationDTO>> listerToutesLesCotisations() {
        return ResponseEntity.ok(cotisationService.listerToutesLesCotisations());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CotisationDTO> obtenirCotisationParId(@PathVariable Long id) {
        return ResponseEntity.ok(cotisationService.obtenirCotisationParId(id));
    }

    @GetMapping("/adhesion/{adhesionId}")
    public ResponseEntity<List<CotisationDTO>> listerParAdhesion(@PathVariable Long adhesionId) {
        return ResponseEntity.ok(cotisationService.listerCotisationsParAdhesion(adhesionId));
    }

    @GetMapping("/tour/{tourId}")
    public ResponseEntity<List<CotisationDTO>> listerParTour(@PathVariable Long tourId) {
        return ResponseEntity.ok(cotisationService.listerCotisationsParTour(tourId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> annulerCotisation(@PathVariable Long id) {
        cotisationService.annulerCotisation(id);
        return ResponseEntity.noContent().build();
    }
}