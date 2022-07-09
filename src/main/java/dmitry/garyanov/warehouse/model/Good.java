package dmitry.garyanov.warehouse.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Good implements IEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ownerID", referencedColumnName = "id")
    private Contractor owner;

    @ManyToOne
    @JoinColumn(name = "shipmentID", referencedColumnName = "id")
    private Shipment shipment;

    private String name;
    private String barcode;
    private Long price;
    private Double weight;
    private Double volume;

}
