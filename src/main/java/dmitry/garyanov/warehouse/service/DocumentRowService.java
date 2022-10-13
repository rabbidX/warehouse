package dmitry.garyanov.warehouse.service;

import com.sun.istack.NotNull;
import dmitry.garyanov.warehouse.model.DocumentRow;
import dmitry.garyanov.warehouse.model.User;
import dmitry.garyanov.warehouse.repository.DocumentRowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class DocumentRowService implements IService {
    @NotNull
    private DocumentRowRepository documentRowRepository;

    public void save (DocumentRow documentRow) {
        documentRowRepository.save(documentRow);
    }

    @Override
    public List<DocumentRow> getAll() {
        return documentRowRepository.findAll(Sort.by("id"));
    }

    public void saveAll(List documentRows) {
        documentRowRepository.saveAll(documentRows);
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
