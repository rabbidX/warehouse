package dmitry.garyanov.warehouse.model;

import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Accessors(chain = true)
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class DocumentRow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "document_id")
    Document document;

    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "goodId")
    Good good;

    int quantity;
    long worth;

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
