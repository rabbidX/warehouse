package dmitry.garyanov.warehouse.model;

import java.time.LocalDateTime;

public interface GoodGroopedRemaining {
    int getQuantity();
    long getWorth();
    LocalDateTime getReceiptDate();

}
