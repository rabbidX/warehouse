package dmitry.garyanov.warehouse.repository;

import dmitry.garyanov.warehouse.model.Shipment;
import dmitry.garyanov.warehouse.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShipmentRepository extends JpaRepository<Shipment, Long> {
}
