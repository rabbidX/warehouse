package dmitry.garyanov.warehouse.controller.rest;

import dmitry.garyanov.warehouse.model.Shipment;
import dmitry.garyanov.warehouse.service.ShipmentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/shipments/")
@AllArgsConstructor
public class ShipmentController {

    private ShipmentService shipmentService;

    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Shipment> getShipment(@PathVariable("id") Long shipmentId) {
        if (shipmentId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Shipment shipment = this.shipmentService.getById(shipmentId);

        if (shipment == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(shipment, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Shipment> saveShipment(@RequestBody @Validated Shipment shipment) {
        HttpHeaders headers = new HttpHeaders();

        if (shipment == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        shipmentService.save(shipment);
        return new ResponseEntity<>(shipment, headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Shipment> updateShipment(@RequestBody @Validated  Shipment shipment) {
        HttpHeaders headers = new HttpHeaders();

        if (shipment == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        shipmentService.save(shipment);
        return new ResponseEntity<>(shipment, headers, HttpStatus.OK);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Shipment>  delete(@PathVariable("id")Long shipmentId) {
        if (shipmentId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Shipment shipment = shipmentService.getById(shipmentId);
        if (shipment == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        shipmentService.delete(shipmentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Shipment>> getAlShipments() {
        List<Shipment> shipments = shipmentService.getAll();
        if (shipments.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(shipments, HttpStatus.OK);

    }
}
