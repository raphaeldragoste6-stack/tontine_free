package raphael.tontinepro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import raphael.tontinepro.entity.Cotisation;

import java.util.List;

@Repository
public interface CotisationRepository extends JpaRepository<Cotisation, Long> {

    List<Cotisation> findByAdhesionId(Long adhesionId);

    List<Cotisation> findByTourId(Long tourId);
}