package se.jaw.jaxrs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.jaw.jaxrs.model.BadUserInputException;
import se.jaw.jaxrs.model.NotFoundException;
import se.jaw.jaxrs.model.UserDto;
import se.jaw.jaxrs.persistence.entity.User;
import se.jaw.jaxrs.persistence.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User saveUser(User user) {

        if (user.getFirstName() == null){
            throw  new BadUserInputException("Firstname can not be null");

        }

        if (user.getLastName()== null) {
            throw  new BadUserInputException("Lastname can not be null");
        }


        return userRepository.save(user);
    }

    @Override
    public UserDto getUser(String id) {
        return userRepository.findById(Long.valueOf(id))
                .map(UserDto::new)
                .orElseThrow(() -> new NotFoundException("User with id " + id + " not found"));
    }

    @Override
    public List<UserDto> getUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteUser(String userId) {
        userRepository.findById(Long.valueOf(userId)).ifPresent(userRepository::delete);
    }
}

