package raphael.tontinepro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import raphael.tontinepro.entity.Membre;

import java.util.Optional;



@Repository
public interface MembreRepository extends JpaRepository<Membre,Long> {

    //  Gère la table des utilisateurs (membres et administrateurs).
    Optional<Membre> findByEmail(String Email);
    boolean existsByEmail(String email);
}
