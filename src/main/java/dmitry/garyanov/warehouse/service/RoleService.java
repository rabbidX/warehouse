package dmitry.garyanov.warehouse.service;

import com.sun.istack.NotNull;
import dmitry.garyanov.warehouse.model.Role;
import dmitry.garyanov.warehouse.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {
    @NotNull
    private RoleRepository roleRepository;

    public void save(Role role) {
        roleRepository.save(role);
    }

    public List<Role> getAll() {
        return roleRepository.findAll();
    }

     public Role getById(long id) {
        return roleRepository.findById(id).get();
    }

    public void saveAll(List roles) {
        roleRepository.saveAll(roles);
    }


}
