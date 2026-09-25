package raphael.tontinepro.service;

import raphael.tontinepro.dto.MembreDTO;

import java.util.List;

public interface MembreService {

    MembreDTO creerMembre(MembreDTO dto);

    List<MembreDTO> listerTousLesMembres();

    MembreDTO obtenirMembreParId(Long id);

    MembreDTO modifierMembre(Long id, MembreDTO dto);

    void supprimerMembre(Long id);
}