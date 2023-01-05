package dmitry.garyanov.warehouse.service;

import com.sun.istack.NotNull;
import dmitry.garyanov.warehouse.model.DocumentRow;
import dmitry.garyanov.warehouse.model.GoodsReceipt;
import dmitry.garyanov.warehouse.model.Remaining;
import dmitry.garyanov.warehouse.repository.DocumentRowRepository;
import dmitry.garyanov.warehouse.repository.RemainingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.print.Doc;
import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DocumentRowService implements IService {
    @NotNull
    private DocumentRowRepository documentRowRepository;
    @NotNull
    private RemainingRepository remainingRepository;
    @NotNull
    private EntityManager entityManager;
    public void save (DocumentRow documentRow) {
        documentRowRepository.save(documentRow);
        updateRemaining(documentRow);
    }

    private void updateRemaining(DocumentRow documentRow) {
       List<Remaining> remainingList = remainingRepository.findByRegistrar(documentRow);
       remainingRepository.deleteAll(remainingList);
       if (documentRow.getDocument() instanceof GoodsReceipt) {
           updateIncomingRemaining(documentRow);
        } else {
           updateOutgoingRemaining(documentRow);
        }
    }

    private void updateOutgoingRemaining(DocumentRow documentRow) {
        List<Remaining> remainingList = remainingRepository.findByRegistrar(documentRow);
        remainingRepository.deleteAll(remainingList);

        String queryText = new StringBuilder()
                .append("SELECT receipt_date, SUM(quantity) quantity, SUM(worth) worth ")
                .append("FROM remaining ")
                .append("WHERE good_id = ?1 ")
                .append("AND date < ?2 ")
                .append("GROUP BY receipt_date ")
                .append("HAVING SUM(quantity) > 0")
                .append("ORDER BY receipt_date")
                .toString();
        Query query = entityManager.createNativeQuery(queryText, Tuple.class);
        query.setParameter(1, documentRow.getGood().getId());
        query.setParameter(2, documentRow.getDate());
        List<Tuple> queryResult = query.getResultList();
        AtomicInteger remainingDistribute = new AtomicInteger(documentRow.getQuantity());
        if (remainingDistribute.get() == 0) {
            return;
        }
        List<Remaining> result = queryResult.stream()
                .map(row -> createRemaining(row, documentRow, remainingDistribute))
                .filter(rem -> rem.getQuantity() != 0)
                .collect(Collectors.toList());
        remainingRepository.saveAll(result);

    }

    private Remaining createRemaining(Tuple tuple, DocumentRow documentRow, AtomicInteger remainingDistribute) {
        int currentQuantity = ((BigInteger)tuple.get("quantity")).intValue();
        int quantity = Math.min(remainingDistribute.get(), currentQuantity);
        long worth = Math.round((double)quantity / currentQuantity * ((BigInteger)tuple.get("worth")).longValue());

        Remaining newRemaining = new Remaining();
        newRemaining.setRegistrar(documentRow)
                .setGood(documentRow.getGood())
                .setReceiptDate((Date)tuple.get("receipt_date"))
                .setDate(documentRow.getDate())
                .setQuantity(-quantity)
                .setWorth(-worth);
        remainingDistribute.getAndAdd(-quantity);
        return newRemaining;
    }

    private void updateIncomingRemaining(DocumentRow documentRow) {

        Remaining record = new Remaining()
                .setGood(documentRow.getGood())
                .setRegistrar(documentRow)
                .setQuantity(documentRow.getQuantity())
                .setWorth(documentRow.getWorth())
                .setReceiptDate(documentRow.getDate())
                .setDate(documentRow.getDate());
        remainingRepository.save(record);
    }
    @Override
    public List<DocumentRow> getAll() {
        return documentRowRepository.findAll(Sort.by("id"));
    }

    public void saveAll(List<DocumentRow> documentRows) {
        for (DocumentRow documentRow: documentRows) {
            save(documentRow);
        }
    }

    public DocumentRow getById(long id) {
        return documentRowRepository.findById(id).get();
    }
}
