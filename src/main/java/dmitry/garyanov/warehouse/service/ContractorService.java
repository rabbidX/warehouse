package dmitry.garyanov.warehouse.service;

import com.sun.istack.NotNull;
import dmitry.garyanov.warehouse.model.Contractor;
import dmitry.garyanov.warehouse.model.User;
import dmitry.garyanov.warehouse.repository.ContractorRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class ContractorService implements IService {
    @NotNull
    private ContractorRepository contractorRepository;

    public void save (Contractor contractor) {
        contractorRepository.save(contractor);
    }

    @Override
    public List<Contractor> getAll() {
        return contractorRepository.findAll();
    }

    public void saveAll(List<Contractor> contractors) {
        contractorRepository.saveAll(contractors);
    }
}
