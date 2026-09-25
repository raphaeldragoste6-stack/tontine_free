package raphael.tontinepro.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import raphael.tontinepro.dto.AdhesionDTO;
import raphael.tontinepro.entity.Adhesion;
import raphael.tontinepro.entity.Membre;
import raphael.tontinepro.entity.Tontine;
import raphael.tontinepro.repository.AdhesionRepository;
import raphael.tontinepro.repository.MembreRepository;
import raphael.tontinepro.repository.TontineRepository;
import raphael.tontinepro.service.AdhesionService;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class AdhesionServiceImpl implements AdhesionService {

    private final AdhesionRepository adhesionRepository;
    private final MembreRepository membreRepository;
    private final TontineRepository tontineRepository;

    @Override
    public AdhesionDTO inscrireMembreATontine(AdhesionDTO dto) {
        Membre membre = membreRepository.findById(dto.getMembreId())
                .orElseThrow(() -> new RuntimeException("Membre non trouvé avec l'id : " + dto.getMembreId()));

        Tontine tontine = tontineRepository.findById(dto.getTontineId())
                .orElseThrow(() -> new RuntimeException("Tontine non trouvée avec l'id : " + dto.getTontineId()));

        // Vérifier si le membre est déjà inscrit à cette tontine
        if (adhesionRepository.existsByMembreIdAndTontineId(dto.getMembreId(), dto.getTontineId())) {
            throw new RuntimeException("Ce membre est déjà inscrit à cette tontine.");
        }

        Adhesion adhesion = Adhesion.builder()
                .membre(membre)
                .tontine(tontine)
                .ordreTour(dto.getOrdreTour())
                .dateAdhesion(LocalDate.now())
                .build();

        Adhesion adhesionSauvee = adhesionRepository.save(adhesion);
        return mapToDTO(adhesionSauvee);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AdhesionDTO> listerToutesLesAdhesions() {
        return adhesionRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<AdhesionDTO> listerAdhesionsParMembre(Long membreId) {
        return adhesionRepository.findByMembreId(membreId).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<AdhesionDTO> listerAdhesionsParTontine(Long tontineId) {
        return adhesionRepository.findByTontineId(tontineId).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public AdhesionDTO obtenirAdhesionParId(Long id) {
        Adhesion adhesion = adhesionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Adhésion non trouvée avec l'id : " + id));
        return mapToDTO(adhesion);
    }

    @Override
    public void resilierAdhesion(Long id) {
        if (!adhesionRepository.existsById(id)) {
            throw new RuntimeException("Adhésion non trouvée avec l'id : " + id);
        }
        adhesionRepository.deleteById(id);
    }

    private AdhesionDTO mapToDTO(Adhesion adhesion) {
        return AdhesionDTO.builder()
                .id(adhesion.getId())
                .membreId(adhesion.getMembre().getId())
                .tontineId(adhesion.getTontine().getId())
                .ordreTour(adhesion.getOrdreTour())
                .dateAdhesion(adhesion.getDateAdhesion())
                .build();
    }
}