package dmitry.garyanov.warehouse.service;

import com.sun.istack.NotNull;
import dmitry.garyanov.warehouse.model.GoodsReceipt;
import dmitry.garyanov.warehouse.model.Remaining;
import dmitry.garyanov.warehouse.repository.RemainingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class RemainingService implements IService {
    @NotNull
    private RemainingRepository remainingRepository;

    public void save (Remaining goodsReceipt) {
        remainingRepository.save(goodsReceipt);
    }

    @Override
    public List<Remaining> getAll() {
        return remainingRepository.findAll(Sort.by("id"));
    }

    public void saveAll(List remainings) {
        remainingRepository.saveAll(remainings);
    }

    public Remaining get(long id) {
        try {
            return remainingRepository.findById(id).get();
        } catch (NoSuchElementException e) {
            return new Remaining();
        }
    }

    public Remaining getById(long id) {
        return remainingRepository.findById(id).get();
    }
}
