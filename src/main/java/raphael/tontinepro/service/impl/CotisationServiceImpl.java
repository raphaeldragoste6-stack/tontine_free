package raphael.tontinepro.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import raphael.tontinepro.dto.CotisationDTO;
import raphael.tontinepro.entity.Adhesion;
import raphael.tontinepro.entity.Cotisation;
import raphael.tontinepro.entity.Tour;
import raphael.tontinepro.repository.AdhesionRepository;
import raphael.tontinepro.repository.CotisationRepository;
import raphael.tontinepro.repository.TourRepository;
import raphael.tontinepro.service.CotisationService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CotisationServiceImpl implements CotisationService {

    private final CotisationRepository cotisationRepository;
    private final AdhesionRepository adhesionRepository;
    private final TourRepository tourRepository;

    @Override
    public CotisationDTO enregistrerCotisation(CotisationDTO dto) {
        Adhesion adhesion = adhesionRepository.findById(dto.getAdhesionId())
                .orElseThrow(() -> new RuntimeException("Adhésion non trouvée avec l'id : " + dto.getAdhesionId()));

        Tour tour = tourRepository.findById(dto.getTourId())
                .orElseThrow(() -> new RuntimeException("Tour non trouvé avec l'id : " + dto.getTourId()));

        Boolean estPaye = dto.getPaye() != null ? dto.getPaye() : true;

        Cotisation cotisation = Cotisation.builder()
                .adhesion(adhesion)
                .tour(tour)
                .montant(dto.getMontant())
                .dateCotisation(LocalDateTime.now())
                .paye(estPaye)
                .build();

        Cotisation cotisationSauvee = cotisationRepository.save(cotisation);
        return mapToDTO(cotisationSauvee);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CotisationDTO> listerToutesLesCotisations() {
        return cotisationRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public CotisationDTO obtenirCotisationParId(Long id) {
        Cotisation cotisation = cotisationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cotisation non trouvée avec l'id : " + id));
        return mapToDTO(cotisation);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CotisationDTO> listerCotisationsParAdhesion(Long adhesionId) {
        return cotisationRepository.findByAdhesionId(adhesionId).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<CotisationDTO> listerCotisationsParTour(Long tourId) {
        return cotisationRepository.findByTourId(tourId).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void annulerCotisation(Long id) {
        if (!cotisationRepository.existsById(id)) {
            throw new RuntimeException("Cotisation non trouvée avec l'id : " + id);
        }
        cotisationRepository.deleteById(id);
    }

    // Méthode permettant de vérifier les tours associés au membre bénéficiaire
    @Transactional(readOnly = true)
    public List<Tour> obtenirToursParMembre(Long membreId) {
        return tourRepository.findByMembreBeneficiaireId(membreId);
    }

    private CotisationDTO mapToDTO(Cotisation cotisation) {
        return CotisationDTO.builder()
                .id(cotisation.getId())
                .adhesionId(cotisation.getAdhesion().getId())
                .tourId(cotisation.getTour().getId())
                .montant(cotisation.getMontant())
                .dateCotisation(cotisation.getDateCotisation())
                .paye(cotisation.getPaye())
                .build();
    }
}