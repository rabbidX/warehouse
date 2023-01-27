package dmitry.garyanov.warehouse.service;

import com.sun.istack.NotNull;
import dmitry.garyanov.warehouse.model.DocumentRow;
import dmitry.garyanov.warehouse.model.GoodsReceipt;
import dmitry.garyanov.warehouse.model.Remaining;
import dmitry.garyanov.warehouse.repository.DocumentRowRepository;
import dmitry.garyanov.warehouse.repository.RemainingRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityManager;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class DocumentRowServiceTest {

    @InjectMocks
    private DocumentRowService documentRowService;
    @Mock
    private DocumentRowRepository documentRowRepository;
    @Mock
    private RemainingRepository remainingRepository;
    @Mock
    private EntityManager entityManager;

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
}