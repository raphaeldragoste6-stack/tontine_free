package raphael.tontinepro.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import raphael.tontinepro.dto.MembreDTO;
import raphael.tontinepro.service.MembreService;

import java.util.List;

@RestController
@RequestMapping("/api/membres")
@RequiredArgsConstructor
public class MembreController {

    private final MembreService membreService;

    @PostMapping
    public ResponseEntity<MembreDTO> inscrireMembre(@Valid @RequestBody MembreDTO dto) {
        MembreDTO nouveauMembre = membreService.creerMembre(dto);
        return new ResponseEntity<>(nouveauMembre, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<MembreDTO>> listerTousLesMembres() {
        return ResponseEntity.ok(membreService.listerTousLesMembres());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MembreDTO> obtenirMembreParId(@PathVariable Long id) {
        return ResponseEntity.ok(membreService.obtenirMembreParId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MembreDTO> mettreAJourMembre(@PathVariable Long id, @Valid @RequestBody MembreDTO dto) {
        return ResponseEntity.ok(membreService.modifierMembre(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerMembre(@PathVariable Long id) {
        membreService.supprimerMembre(id);
        return ResponseEntity.noContent().build();
    }
}