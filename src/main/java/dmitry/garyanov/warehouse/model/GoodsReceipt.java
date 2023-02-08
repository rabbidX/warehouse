package dmitry.garyanov.warehouse.model;

import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Accessors(chain = true)
@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@DiscriminatorValue("GoodsReceipt")
public class GoodsReceipt extends Document {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        GoodsReceipt that = (GoodsReceipt) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode() + id.intValue();
    }
}
