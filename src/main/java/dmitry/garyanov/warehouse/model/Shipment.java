package dmitry.garyanov.warehouse.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Shipment implements IEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "contractorID", referencedColumnName = "id")
    private Contractor contractor;

    private String name;
}
