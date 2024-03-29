package dmitry.garyanov.warehouse.service;

import com.sun.istack.NotNull;
import dmitry.garyanov.warehouse.model.Shipment;
import dmitry.garyanov.warehouse.repository.ShipmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ShipmentService {
    @NotNull
    private ShipmentRepository shipmentRepository;

    public void save (Shipment shipment) {
        shipmentRepository.save(shipment);
    }

    public List<Shipment> getAll() {
        return shipmentRepository.findAll();
    }

    public void saveAll(List shipments) {
        shipmentRepository.saveAll(shipments);
    }

    public Shipment getById(long id) {
        return shipmentRepository.findById(id).get();
    }

    public void update(Shipment shipment) {
        shipmentRepository.save(shipment);
    }

    public void delete(Long id) {
        shipmentRepository.deleteById(id);
    }
}
