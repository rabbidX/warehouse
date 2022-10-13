package dmitry.garyanov.warehouse.service;

import com.sun.istack.NotNull;
import dmitry.garyanov.warehouse.model.DocumentRow;
import dmitry.garyanov.warehouse.model.GoodsReceipt;
import dmitry.garyanov.warehouse.repository.GoodReceiptRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class GoodReceiptService implements IService {
    @NotNull
    private GoodReceiptRepository goodReceiptRepository;

    public void save (GoodsReceipt goodsReceipt) {
        goodReceiptRepository.save(goodsReceipt);
    }

    @Override
    public List<GoodsReceipt> getAll() {
        return goodReceiptRepository.findAll(Sort.by("id"));
    }

    public void saveAll(List goodReceipts) {
        goodReceiptRepository.saveAll(goodReceipts);
    }

    public GoodsReceipt get(long id) {
        try {
            return goodReceiptRepository.findById(id).get();
        } catch (NoSuchElementException e) {
            return new GoodsReceipt();
        }
    }

    public GoodsReceipt getById(long id) {
        return goodReceiptRepository.findById(id).get();
    }
}
