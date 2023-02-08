package dmitry.garyanov.warehouse.service;

import com.sun.istack.NotNull;
import dmitry.garyanov.warehouse.model.DocumentRow;
import dmitry.garyanov.warehouse.model.GoodGroopedRemaining;
import dmitry.garyanov.warehouse.model.GoodsReceipt;
import dmitry.garyanov.warehouse.model.Remaining;
import dmitry.garyanov.warehouse.repository.DocumentRowRepository;
import dmitry.garyanov.warehouse.repository.RemainingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DocumentRowService {
    @NotNull
    private DocumentRowRepository documentRowRepository;
    @NotNull
    private RemainingRepository remainingRepository;

    //    public void save (POJODocumentRow documentRow) {
    public void save (DocumentRow documentRow) {
        //DocumentRow row = DocumentRowMapper.toEntity(documentRow);
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

        AtomicInteger remainingDistribute = new AtomicInteger(documentRow.getQuantity());
        if (remainingDistribute.get() == 0) {
            return;
        }

        Long goodId = documentRow.getGood().getId();
        LocalDateTime date = documentRow.getDate();
        Set<GoodGroopedRemaining> currentRemaining = remainingRepository.getRemainingByGoodid_Date(goodId, date);
        List<Remaining> result = currentRemaining.stream().map(row -> createRemaining(row, documentRow, remainingDistribute))
                .filter(rem -> rem.getQuantity() != 0)
                .collect(Collectors.toList());

        remainingRepository.saveAll(result);
    }

    private Remaining createRemaining(GoodGroopedRemaining currentRemaining, DocumentRow documentRow, AtomicInteger remainingDistribute) {
        int currentQuantity = currentRemaining.getQuantity();
        int quantity = Math.min(remainingDistribute.get(), currentQuantity);
        long worth = Math.round((double)quantity / currentQuantity * currentRemaining.getWorth());

        Remaining newRemaining = new Remaining();
        newRemaining.setRegistrar(documentRow)
                .setGood(documentRow.getGood())
                .setReceiptDate(currentRemaining.getReceiptDate())
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
