package dmitry.garyanov.warehouse.model;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Accessors(chain = true)
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Remaining {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "good_id")
    private Good good;

    private LocalDateTime receiptDate;
    private LocalDateTime date;
    private int quantity;
    private long worth;
    @ManyToOne
    private DocumentRow registrar;

}
