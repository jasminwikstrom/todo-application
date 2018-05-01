package se.jaw.jaxrs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.jaw.jaxrs.model.UserDto;
import se.jaw.jaxrs.persistence.entity.User;
import se.jaw.jaxrs.persistence.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<UserDto> getUser(String id) {
        return userRepository.findById(Long.valueOf(id)).map(UserDto::new);
    }

    @Override
    public List<UserDto> getUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserDto::new)
                .collect(Collectors.toList());
    }
}
