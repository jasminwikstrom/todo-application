package se.jaw.jaxrs.service;

import se.jaw.jaxrs.model.ImportanceType;
import se.jaw.jaxrs.model.TodoDto;
import se.jaw.jaxrs.persistence.entity.Todo;

import java.util.List;
import java.util.Optional;

public interface TodoService {

    TodoDto saveTodo(Todo todo);

    Optional<TodoDto> getTodo(String id);

    List<TodoDto> getTodos(String userId, ImportanceType importanceType);

    void deleteTodo(String id);

    Todo todoAssignment(String id, String userId);
}