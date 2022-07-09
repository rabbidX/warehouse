package dmitry.garyanov.warehouse.service;

import com.sun.istack.NotNull;
import dmitry.garyanov.warehouse.model.Role;
import dmitry.garyanov.warehouse.model.Shipment;
import dmitry.garyanov.warehouse.repository.ShipmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShipmentService  implements IService {
    @NotNull
    private ShipmentRepository shipmentRepository;

    public void save (Shipment shipment) {
        shipmentRepository.save(shipment);
    }

    @Override
    public List<Shipment> getAll() {
        return shipmentRepository.findAll();
    }

    public void saveAll(List shipments) {
        shipmentRepository.saveAll(shipments);
    }
}
