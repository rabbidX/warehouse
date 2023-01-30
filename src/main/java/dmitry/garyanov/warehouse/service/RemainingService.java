package dmitry.garyanov.warehouse.service;

import com.sun.istack.NotNull;
import dmitry.garyanov.warehouse.model.Remaining;
import dmitry.garyanov.warehouse.repository.RemainingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class RemainingService {
    @NotNull
    private RemainingRepository remainingRepository;

    public List<Remaining> getAll() {
        return remainingRepository.findAll(Sort.by("id"));
    }

    public Remaining getById(long id) {
        return remainingRepository.findById(id).get();
    }
}
