package se.jaw.jaxrs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.jaw.jaxrs.model.BadUserInputException;
import se.jaw.jaxrs.model.ImportanceType;
import se.jaw.jaxrs.model.NotFoundException;
import se.jaw.jaxrs.model.TodoDto;
import se.jaw.jaxrs.persistence.entity.Todo;
import se.jaw.jaxrs.persistence.repository.TodoRepository;
import se.jaw.jaxrs.persistence.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TodoServiceImpl implements TodoService {

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public TodoDto saveTodo(Todo todo) {

        if (todo.getImportance() == null) {
            throw new BadUserInputException("importance can not be null");
        }

        if (todo.getDescription() == null) {
            throw new BadUserInputException("description can not be null");
        }

        return new TodoDto(todoRepository.save(todo));
    }

    @Override
    public void deleteTodo(String id) {

        if (id == null) {
            throw new BadUserInputException("id can not be null");
        }

        todoRepository.findById(Long.valueOf(id)).ifPresent(todoRepository::delete);
    }


    @Override
    public Todo todoAssignment(String id, String userId) {

        if (id == null) {
            throw new BadUserInputException("id can not be null");
        }

        if (userId == null) {
            throw new BadUserInputException("userId can not be null");
        }

        if (!userRepository.existsById(Long.valueOf(userId))) {
            throw new NotFoundException("User with id " + userId + " was not found");
        }

        return todoRepository.findById(Long.valueOf(id))
                .map(todo -> {
                    todo.setUserId(Long.valueOf(userId));
                    return todoRepository.save(todo);
                }).orElseThrow(() -> new BadUserInputException("Todo with id " + id + " was not found"));
    }

    @Override
    public Optional<TodoDto> getTodo(String id) {

        if (id == null) {
            throw new BadUserInputException("id can not be null");
        }

        return todoRepository.findById(Long.valueOf(id))
                .map(TodoDto::new);
    }

    @Override
    public List<TodoDto> getTodos(String userId, ImportanceType importanceType) {
        return todoRepository.findAllByQuery(
                importanceType != null ? importanceType.name() : null,
                userId != null ? Long.valueOf(userId) : null)
                .stream()
                .map(TodoDto::new)
                .collect(Collectors.toList());
    }
}
