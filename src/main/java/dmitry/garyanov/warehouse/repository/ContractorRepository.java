package dmitry.garyanov.warehouse.repository;

import dmitry.garyanov.warehouse.model.Contractor;
import dmitry.garyanov.warehouse.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractorRepository extends JpaRepository<Contractor, Long> {
}
