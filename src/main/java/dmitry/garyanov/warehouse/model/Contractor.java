package dmitry.garyanov.warehouse.model;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;

@Accessors(chain = true)
@Entity
@Data
@ToString(exclude = "shipments")
public class Contractor implements IEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "contractor")
    private List<Shipment> shipments;

    @Override
    public Long getId() {
        return this.id;
    }
}
