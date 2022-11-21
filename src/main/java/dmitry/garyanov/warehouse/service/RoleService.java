package dmitry.garyanov.warehouse.service;

import com.sun.istack.NotNull;
import dmitry.garyanov.warehouse.model.Contractor;
import dmitry.garyanov.warehouse.model.IEntity;
import dmitry.garyanov.warehouse.model.Role;
import dmitry.garyanov.warehouse.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class RoleService  implements IService {
    @NotNull
    private RoleRepository roleRepository;

    public void save(Role role) {
        roleRepository.save(role);
    }

    @Override
    public List<Role> getAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role getById(long id) {
        return roleRepository.findById(id).get();
    }

    public void saveAll(List roles) {
        roleRepository.saveAll(roles);
    }


}
