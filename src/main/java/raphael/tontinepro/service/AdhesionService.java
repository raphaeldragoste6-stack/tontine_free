package raphael.tontinepro.service;

import raphael.tontinepro.dto.AdhesionDTO;
import java.util.List;

public interface AdhesionService {
    AdhesionDTO inscrireMembreATontine(AdhesionDTO dto);
    List<AdhesionDTO> listerToutesLesAdhesions();
    List<AdhesionDTO> listerAdhesionsParMembre(Long membreId);
    List<AdhesionDTO> listerAdhesionsParTontine(Long tontineId);
    AdhesionDTO obtenirAdhesionParId(Long id);
    void resilierAdhesion(Long id);
}