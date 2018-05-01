package se.jaw.jaxrs.service;

import se.jaw.jaxrs.model.UserDto;
import se.jaw.jaxrs.persistence.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User saveUser(User user);

    Optional<UserDto> getUser(String id);

    List<UserDto> getUsers();

    void deleteUser(String userId);
}
