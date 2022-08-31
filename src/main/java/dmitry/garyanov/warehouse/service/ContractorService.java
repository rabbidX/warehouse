package dmitry.garyanov.warehouse.service;

import com.sun.istack.NotNull;
import dmitry.garyanov.warehouse.model.Contractor;
import dmitry.garyanov.warehouse.model.Role;
import dmitry.garyanov.warehouse.model.User;
import dmitry.garyanov.warehouse.repository.ContractorRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.NoSuchElementException;

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

    public Contractor get(long id) {
        try {
            return  contractorRepository.findById(id).get();
        } catch(NoSuchElementException e) {
            Contractor newContractor = new Contractor();
            return newContractor;
        }
    }

    public Contractor getById(long id) {
        return  contractorRepository.findById(id).get();
    }

    public void update(Contractor contractor) {
        contractorRepository.save(contractor);
    }

    public void delete(Long id) {
        contractorRepository.deleteById(id);
    }
}
