package dmitry.garyanov.warehouse.controller.rest;

import dmitry.garyanov.warehouse.model.DocumentRow;
import dmitry.garyanov.warehouse.service.DocumentRowService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/rows/")
@AllArgsConstructor
public class DocumentRowController {
    private DocumentRowService documentRowService;

    @RequestMapping(method = RequestMethod.POST)
    public void saveAll(List<DocumentRow> documentRows) {
        documentRows.sort(Comparator.comparing(DocumentRow::getDate));
        documentRowService.saveAll(documentRows);
    }
}
