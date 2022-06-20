package dmitry.garyanov.warehouse.service;

import dmitry.garyanov.warehouse.model.RoleEntity;
import dmitry.garyanov.warehouse.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public void save(RoleEntity roleEntity) {
        roleRepository.save(roleEntity);
    }

    public List<RoleEntity> getAll() {
        return roleRepository.findAll();
    }
}
