package dmitry.garyanov.warehouse.service;

import com.sun.istack.NotNull;
import dmitry.garyanov.warehouse.model.Shipment;
import dmitry.garyanov.warehouse.model.User;
import dmitry.garyanov.warehouse.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserService  implements IService {
    @NotNull
    private UserRepository userRepository;

    public void save (User user) {
        userRepository.save(user);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    public void saveAll(List users) {
        userRepository.saveAll(users);
    }

    public User get(long id) {
        try {
            return userRepository.findById(id).get();
        } catch (NoSuchElementException e) {
            return new User();
        }
    }
}
