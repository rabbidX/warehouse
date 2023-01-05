package dmitry.garyanov.warehouse.repository;

import dmitry.garyanov.warehouse.model.DocumentRow;
import dmitry.garyanov.warehouse.model.Remaining;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.Date;
import java.util.List;

@Repository
public interface RemainingRepository extends JpaRepository<Remaining, Long> {
    List<Remaining> findByRegistrar(DocumentRow registrar);
}
