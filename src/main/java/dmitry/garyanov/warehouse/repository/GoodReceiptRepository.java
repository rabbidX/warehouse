package dmitry.garyanov.warehouse.repository;

import dmitry.garyanov.warehouse.model.GoodsReceipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoodReceiptRepository extends JpaRepository<GoodsReceipt, Long> {
}
