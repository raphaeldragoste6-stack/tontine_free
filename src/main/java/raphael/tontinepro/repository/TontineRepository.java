package raphael.tontinepro.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import raphael.tontinepro.entity.Tontine;

@Repository
public interface TontineRepository extends JpaRepository<Tontine ,Long> {

    }
