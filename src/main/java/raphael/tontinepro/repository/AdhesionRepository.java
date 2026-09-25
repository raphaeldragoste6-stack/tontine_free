package raphael.tontinepro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import raphael.tontinepro.entity.Adhesion;

import java.util.List;

@Repository
public interface AdhesionRepository extends JpaRepository<Adhesion, Long> {

    boolean existsByMembreIdAndTontineId(Long membreId, Long tontineId);

    List<Adhesion> findByMembreId(Long membreId);

    List<Adhesion> findByTontineId(Long tontineId);
}