package dmitry.garyanov.warehouse.repository;

import dmitry.garyanov.warehouse.model.Remaining;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RemainingRepository extends JpaRepository<Remaining, Long> {
}
