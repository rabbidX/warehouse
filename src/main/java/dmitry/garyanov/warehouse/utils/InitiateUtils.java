package dmitry.garyanov.warehouse.utils;

import dmitry.garyanov.warehouse.model.*;
import dmitry.garyanov.warehouse.service.*;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class InitiateUtils  implements CommandLineRunner {
    private DocumentRowService documentRowService;
    @Override
    public void run(String... args) {
        List<DocumentRow> documentRows = documentRowService.getAll();
        documentRows.sort(Comparator.comparing(DocumentRow::getDate));
        documentRowService.saveAll(documentRows);
    }

}
