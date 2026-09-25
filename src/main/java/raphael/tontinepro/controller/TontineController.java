package raphael.tontinepro.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import raphael.tontinepro.dto.TontineDTO;
import raphael.tontinepro.service.TontineService;

import java.util.List;

@RestController
@RequestMapping("/api/tontines")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class TontineController {

    private final TontineService tontineService;

    @PostMapping
    public ResponseEntity<TontineDTO> creerTontine(@Valid @RequestBody TontineDTO dto) {
        TontineDTO nouvelleTontine = tontineService.creerTontine(dto);
        return new ResponseEntity<>(nouvelleTontine, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TontineDTO>> listerToutesLesTontines() {
        return ResponseEntity.ok(tontineService.listerToutesLesTontines());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TontineDTO> obtenirTontineParId(@PathVariable Long id) {
        return ResponseEntity.ok(tontineService.obtenirTontineParId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TontineDTO> modifierTontine(@PathVariable Long id, @Valid @RequestBody TontineDTO dto) {
        return ResponseEntity.ok(tontineService.modifierTontine(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerTontine(@PathVariable Long id) {
        tontineService.supprimerTontine(id);
        return ResponseEntity.noContent().build();
    }
}