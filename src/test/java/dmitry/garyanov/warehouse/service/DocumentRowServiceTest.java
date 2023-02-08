package dmitry.garyanov.warehouse.service;

import com.sun.istack.NotNull;
import dmitry.garyanov.warehouse.model.DocumentRow;
import dmitry.garyanov.warehouse.model.GoodsReceipt;
import dmitry.garyanov.warehouse.model.Remaining;
import dmitry.garyanov.warehouse.model.Shipment;
import dmitry.garyanov.warehouse.repository.DocumentRowRepository;
import dmitry.garyanov.warehouse.repository.RemainingRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class DocumentRowServiceTest {

    @InjectMocks
    private DocumentRowService documentRowService;
    @Mock
    private DocumentRowRepository documentRowRepository;
    @Mock
    private RemainingRepository remainingRepository;

    @Test
    void save() {
        //given
        DocumentRow documentRow = new DocumentRow();
        documentRow.setDocument(new GoodsReceipt());
        List<Remaining> remainingList = new ArrayList<>();
        Mockito.when(remainingRepository.findByRegistrar(any())).thenReturn(remainingList);
        //when
        documentRowService.save(documentRow);
        //then
        Mockito.verify(documentRowRepository).save(documentRow);
        Mockito.verify(remainingRepository).deleteAll(remainingList);
    }

    @Test
    void saveOutgoingRow() {
        //given
        DocumentRow documentRow = new DocumentRow();
        documentRow.setDocument(new Shipment());
        LocalDateTime date = LocalDateTime.of(1973,03,04, 0, 0, 0);
        Mockito.when(remainingRepository.getRemainingByGoodid_Date(1L, date));



    }
}