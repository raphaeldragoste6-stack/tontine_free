package raphael.tontinepro.service;

import raphael.tontinepro.dto.TontineDTO;
import java.util.List;

public interface TontineService {
    TontineDTO creerTontine(TontineDTO dto);
    List<TontineDTO> listerToutesLesTontines();
    TontineDTO obtenirTontineParId(Long id);
    TontineDTO modifierTontine(Long id, TontineDTO dto);
    void supprimerTontine(Long id);
}