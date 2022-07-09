package dmitry.garyanov.warehouse.repository;

import dmitry.garyanov.warehouse.model.Good;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoodRepository extends JpaRepository<Good, Long> {
}
