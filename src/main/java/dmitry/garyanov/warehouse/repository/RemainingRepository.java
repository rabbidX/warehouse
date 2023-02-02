package dmitry.garyanov.warehouse.repository;

import dmitry.garyanov.warehouse.model.DocumentRow;
import dmitry.garyanov.warehouse.model.GoodGroopedRemaining;
import dmitry.garyanov.warehouse.model.Remaining;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Repository
public interface RemainingRepository extends JpaRepository<Remaining, Long> {
    List<Remaining> findByRegistrar(DocumentRow registrar);

    @Query(nativeQuery = true, value = "SELECT receipt_date, SUM(quantity) quantity, SUM(worth) worth " +
            "FROM remaining " + "WHERE good_id = ?1 " + "AND date < ?2 " + "GROUP BY receipt_date " + "HAVING SUM(quantity) > 0 "
            + "ORDER BY receipt_date")
    Set<GoodGroopedRemaining> getRemainingByGoodid_Date(Long goodId, Date date);

//    @Query(nativeQuery = true, value = "SELECT good_id," +
//            " receipt_date," +
//            " SUM(quantity) AS quantity," +
//            " SUM(worth) AS worth, " +
//            "date, id  FROM remaining GROUP BY  date, id, receipt_date, good_id")
//    Collection<Remaining> getRemainingByGoodid_Date();

}
