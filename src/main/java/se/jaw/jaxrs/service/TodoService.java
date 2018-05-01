package se.jaw.jaxrs.service;

import se.jaw.jaxrs.model.TodoDto;
import se.jaw.jaxrs.persistence.entity.Todo;


import java.util.List;
import java.util.Optional;

public interface TodoService {

    Todo saveTodo(Todo todo);

    Optional<TodoDto> getTodos(String id);

    List<TodoDto> getTodos();

}