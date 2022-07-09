package dmitry.garyanov.warehouse.service;

import com.sun.istack.NotNull;
import dmitry.garyanov.warehouse.model.Good;
import dmitry.garyanov.warehouse.model.Role;
import dmitry.garyanov.warehouse.repository.GoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GoodService  implements IService {
    @NotNull
    private GoodRepository goodRepository;

    public void save (Good good) {
        goodRepository.save(good);
    }
    @Override
    public List<Good> getAll() {
        return goodRepository.findAll();
    }

    public void saveAll(List goods) {
        goodRepository.saveAll(goods);
    }
}
