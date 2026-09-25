package raphael.tontinepro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import raphael.tontinepro.entity.Tour;

import java.util.List;

@Repository
public interface TourRepository extends JpaRepository<Tour, Long> {

    // 1. Lister tous les tours d'une tontine
    List<Tour> findByTontineId(Long tontineId);

    // 2. Lister les tours associés à un membre spécifique dans une tontine
    List<Tour> findByTontineIdAndMembreBeneficiaireId(Long tontineId, Long membreId);

    // 3. Lister les tours par membre bénéficiaire
    List<Tour> findByMembreBeneficiaireId(Long membreId);

}