package se.jaw.jaxrs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.jaw.jaxrs.model.ImportanceType;
import se.jaw.jaxrs.model.TodoDto;
import se.jaw.jaxrs.persistence.entity.Todo;
import se.jaw.jaxrs.persistence.repository.TodoRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TodoServiceImpl implements TodoService {

    @Autowired
    private TodoRepository todoRepository;

    @Override
    public TodoDto saveTodo(Todo todo) {
        return new TodoDto(todoRepository.save(todo));
    }

    @Override
    public Optional<TodoDto> getTodo(String id) {

        return todoRepository.findById(Long.valueOf(id))
                .map(TodoDto::new);
    }

    @Override
    public List<TodoDto> getTodos(String userId, ImportanceType importanceType){
        return todoRepository.findAllByQuery(
                importanceType != null ? importanceType.name() : null,
                userId != null ? Long.valueOf(userId) : null)
                .stream()
                .map(TodoDto::new)
                .collect(Collectors.toList());

    }
}
