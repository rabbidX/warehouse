package dmitry.garyanov.warehouse.service;

import com.sun.istack.NotNull;
import dmitry.garyanov.warehouse.model.User;
import dmitry.garyanov.warehouse.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
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
        return userRepository.findAll(Sort.by("id"));
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

    public User getById(long id) {
        return userRepository.findById(id).get();
    }

    public User getByName(String name) {
        List<User> userList = userRepository.findByName(name);
        if (userList.size() == 1) {
            return userList.get(0);
        }
        if (userList.size() == 0) {
            throw new UsernameNotFoundException("User doesn't exist");
        }
        throw new UsernameNotFoundException("Founded more than one user named " + name);
    }
}
