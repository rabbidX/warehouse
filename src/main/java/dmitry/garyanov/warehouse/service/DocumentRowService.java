package dmitry.garyanov.warehouse.service;

import com.sun.istack.NotNull;
import dmitry.garyanov.warehouse.model.DocumentRow;
import dmitry.garyanov.warehouse.model.Remaining;
import dmitry.garyanov.warehouse.model.User;
import dmitry.garyanov.warehouse.repository.DocumentRowRepository;
import dmitry.garyanov.warehouse.repository.RemainingRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

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
       if (documentRow.getShipment() != null) {
            updateOutgoingRemaining(documentRow);
        } else {
            updateIncomingRemaining(documentRow);
        }
    }

    private void updateOutgoingRemaining(DocumentRow documentRow) {
        List<Remaining> remainingList = remainingRepository.findByRegistrar(documentRow);
        remainingRepository.deleteAll(remainingList);

        Query query = entityManager.createNativeQuery("SELECT receipt_date, quantity, worth FROM remaining where good_id = ?1 and date < ?2");
        query.setParameter(1, documentRow.getGood().getId());
        query.setParameter(2, documentRow.getDate());
        List result = query.getResultList();
        System.out.println(result);
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

    public DocumentRow get(long id) {
        try {
            return documentRowRepository.findById(id).get();
        } catch (NoSuchElementException e) {
            return new DocumentRow();
        }
    }

    public DocumentRow getById(long id) {
        return documentRowRepository.findById(id).get();
    }
}
