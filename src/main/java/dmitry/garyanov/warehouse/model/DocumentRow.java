package dmitry.garyanov.warehouse.model;

import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Accessors(chain = true)
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class DocumentRow  implements IEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "shipmentId")
    Shipment shipment;

    @ManyToOne
    @JoinColumn(name = "goodsReceiptId")
    GoodsReceipt goodsReceipt;

    @ManyToOne
    @JoinColumn(name = "goodId")
    Good good;

    int Quantity;
    int Worth;
    @Override
    public Long getId() {
        return this.id;
    }

    public DocumentRow setShipment(Shipment shipment) throws Exception {
        if (this.goodsReceipt != null) {
            throw new Exception("document row " + id + " setShipment exception. Good receipt must be null");
        }
        this.shipment = shipment;
        return this;
    }

    public DocumentRow setGoodsReceipt(GoodsReceipt goodsReceipt) throws Exception{
        if (this.shipment != null) {
            throw new Exception("document row " + id + " setGoodsReceipt exception. Shipment must be null");
        }
        this.goodsReceipt = goodsReceipt;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        DocumentRow that = (DocumentRow) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode() + id.intValue();
    }
}
