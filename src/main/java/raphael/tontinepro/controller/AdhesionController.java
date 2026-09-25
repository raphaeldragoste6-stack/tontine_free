package raphael.tontinepro.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import raphael.tontinepro.dto.AdhesionDTO;
import raphael.tontinepro.service.AdhesionService;

import java.util.List;

@RestController
@RequestMapping("/api/adhesions")
@CrossOrigin(origins = "*")
public class AdhesionController {

    private final AdhesionService adhesionService;

    public AdhesionController(AdhesionService adhesionService) {
        this.adhesionService = adhesionService;
    }

    @PostMapping
    public ResponseEntity<AdhesionDTO> inscrireMembre(@Valid @RequestBody AdhesionDTO dto) {
        AdhesionDTO nouvelleAdhesion = adhesionService.inscrireMembreATontine(dto);
        return new ResponseEntity<>(nouvelleAdhesion, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AdhesionDTO>> listerToutesLesAdhesions() {
        return ResponseEntity.ok(adhesionService.listerToutesLesAdhesions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdhesionDTO> obtenirAdhesionParId(@PathVariable Long id) {
        return ResponseEntity.ok(adhesionService.obtenirAdhesionParId(id));
    }

    @GetMapping("/membre/{membreId}")
    public ResponseEntity<List<AdhesionDTO>> listerParMembre(@PathVariable Long membreId) {
        return ResponseEntity.ok(adhesionService.listerAdhesionsParMembre(membreId));
    }

    @GetMapping("/tontine/{tontineId}")
    public ResponseEntity<List<AdhesionDTO>> listerParTontine(@PathVariable Long tontineId) {
        return ResponseEntity.ok(adhesionService.listerAdhesionsParTontine(tontineId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> resilierAdhesion(@PathVariable Long id) {
        adhesionService.resilierAdhesion(id);
        return ResponseEntity.noContent().build();
    }
}