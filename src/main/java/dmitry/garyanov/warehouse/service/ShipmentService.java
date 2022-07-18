package dmitry.garyanov.warehouse.service;

import com.sun.istack.NotNull;
import dmitry.garyanov.warehouse.model.Role;
import dmitry.garyanov.warehouse.model.Shipment;
import dmitry.garyanov.warehouse.repository.ShipmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

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

    public Shipment get(long id) {
        try {
            return  shipmentRepository.findById(id).get();
        } catch(NoSuchElementException e) {
            Shipment newShipment = new Shipment();
            return newShipment;
        }
    }
}
