package raphael.tontinepro.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import raphael.tontinepro.dto.MembreDTO;
import raphael.tontinepro.entity.Membre;
import raphael.tontinepro.enums.StatutMembre;
import raphael.tontinepro.repository.MembreRepository;
import raphael.tontinepro.service.MembreService;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class MembreServiceImpl implements MembreService {

    private final MembreRepository membreRepository;

    @Override
    public MembreDTO creerMembre(MembreDTO dto) {
        LocalDate dateAdhesion = (dto.getDateAdhesion() != null) ? dto.getDateAdhesion() : LocalDate.now();
        StatutMembre statut = (dto.getStatut() != null) ? dto.getStatut() : StatutMembre.ACTIF;

        Membre membre = Membre.builder()
                .nom(dto.getNom())
                .prenom(dto.getPrenom())
                .email(dto.getEmail())
                .telephone(dto.getTelephone())
                .dateAdhesion(dateAdhesion)
                .statut(statut)
                .build();

        Membre membreSauve = membreRepository.save(membre);
        return mapToDTO(membreSauve);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MembreDTO> listerTousLesMembres() {
        return membreRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public MembreDTO obtenirMembreParId(Long id) {
        Membre membre = membreRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Membre non trouvé avec l'id : " + id));
        return mapToDTO(membre);
    }

    @Override
    public MembreDTO modifierMembre(Long id, MembreDTO dto) {
        Membre membre = membreRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Membre non trouvé avec l'id : " + id));

        membre.setNom(dto.getNom());
        membre.setPrenom(dto.getPrenom());
        membre.setEmail(dto.getEmail());
        membre.setTelephone(dto.getTelephone());
        if (dto.getStatut() != null) {
            membre.setStatut(dto.getStatut());
        }

        Membre membreMisAJour = membreRepository.save(membre);
        return mapToDTO(membreMisAJour);
    }

    @Override
    public void supprimerMembre(Long id) {
        if (!membreRepository.existsById(id)) {
            throw new RuntimeException("Membre non trouvé avec l'id : " + id);
        }
        membreRepository.deleteById(id);
    }

    private MembreDTO mapToDTO(Membre membre) {
        return MembreDTO.builder()
                .id(membre.getId())
                .nom(membre.getNom())
                .prenom(membre.getPrenom())
                .email(membre.getEmail())
                .telephone(membre.getTelephone())
                .dateAdhesion(membre.getDateAdhesion())
                .statut(membre.getStatut())
                .build();
    }
}