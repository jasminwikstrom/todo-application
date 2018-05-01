package se.jaw.jaxrs.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import se.jaw.jaxrs.persistence.entity.User;

import java.util.List;
import java.util.stream.Collectors;

@JsonIgnoreProperties(ignoreUnknown = true)
public final class UserDto {

    private String id;
    private String firstName;
    private String lastName;
    private List<TodoDto> todos;

    public UserDto() {}

    public UserDto(User user) {
        this.id = user.getId().toString();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();

        this.todos = user.getTodos().stream().map(TodoDto::new).collect(Collectors.toList());
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public List<TodoDto> getTodos() {
        return todos;
    }
}
