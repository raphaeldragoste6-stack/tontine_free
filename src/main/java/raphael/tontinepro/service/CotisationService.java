package raphael.tontinepro.service;

import raphael.tontinepro.dto.CotisationDTO;

import java.util.List;

public interface CotisationService {

    CotisationDTO enregistrerCotisation(CotisationDTO dto);

    List<CotisationDTO> listerToutesLesCotisations();

    CotisationDTO obtenirCotisationParId(Long id);

    List<CotisationDTO> listerCotisationsParAdhesion(Long adhesionId);

    List<CotisationDTO> listerCotisationsParTour(Long tourId);

    void annulerCotisation(Long id);
}