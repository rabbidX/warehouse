package dmitry.garyanov.warehouse.model;

import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Accessors(chain = true)
@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@DiscriminatorValue("Shipment")
public class Shipment extends Document implements IEntity {

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "contractorId", referencedColumnName = "id")
    @ToString.Exclude
    private Contractor contractor;

    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Shipment shipment = (Shipment) o;
        return id != null && Objects.equals(id, shipment.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode() + id.intValue();
    }

}
