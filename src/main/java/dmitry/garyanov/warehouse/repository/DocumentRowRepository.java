package dmitry.garyanov.warehouse.repository;

import dmitry.garyanov.warehouse.model.DocumentRow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRowRepository extends JpaRepository<DocumentRow, Long> {
}
