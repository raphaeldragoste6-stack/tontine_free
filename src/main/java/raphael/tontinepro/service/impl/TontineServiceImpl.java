package raphael.tontinepro.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import raphael.tontinepro.dto.TontineDTO;
import raphael.tontinepro.entity.Tontine;
import raphael.tontinepro.repository.TontineRepository;
import raphael.tontinepro.service.TontineService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class TontineServiceImpl implements TontineService {

    private final TontineRepository tontineRepository;

    @Override
    public TontineDTO creerTontine(TontineDTO dto) {
        Tontine tontine = Tontine.builder()
                .nom(dto.getNom())
                .description(dto.getDescription())
                .montantCotisation(dto.getMontantCotisation())
                .frequence(dto.getFrequence())
                .build();

        Tontine tontineSauvee = tontineRepository.save(tontine);
        return mapToDTO(tontineSauvee);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TontineDTO> listerToutesLesTontines() {
        return tontineRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public TontineDTO obtenirTontineParId(Long id) {
        Tontine tontine = tontineRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tontine non trouvée avec l'id : " + id));
        return mapToDTO(tontine);
    }

    @Override
    public TontineDTO modifierTontine(Long id, TontineDTO dto) {
        Tontine tontine = tontineRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tontine non trouvée avec l'id : " + id));

        tontine.setNom(dto.getNom());
        tontine.setDescription(dto.getDescription());
        tontine.setMontantCotisation(dto.getMontantCotisation());
        tontine.setFrequence(dto.getFrequence());

        Tontine tontineMisAJour = tontineRepository.save(tontine);
        return mapToDTO(tontineMisAJour);
    }

    @Override
    public void supprimerTontine(Long id) {
        if (!tontineRepository.existsById(id)) {
            throw new RuntimeException("Tontine non trouvée avec l'id : " + id);
        }
        tontineRepository.deleteById(id);
    }

    private TontineDTO mapToDTO(Tontine tontine) {
        return TontineDTO.builder()
                .id(tontine.getId())
                .nom(tontine.getNom())
                .description(tontine.getDescription())
                .montantCotisation(tontine.getMontantCotisation())
                .frequence(tontine.getFrequence())
                .dateCreation(tontine.getDateCreation())
                .build();
    }
}