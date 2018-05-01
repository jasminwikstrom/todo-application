package se.jaw.jaxrs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
    public Todo saveTodo(Todo todo) {
        return null;
    }


    @Override
    public Optional<TodoDto> getTodos(String id) {
        return todoRepository.findById(Long.valueOf(id)).map(TodoDto::new);
    }

  //  @Override
    //public Optional<TodoDto> getTodos(String id) {
      //  return Optional.empty();
   // }

    @Override
    public List<TodoDto> getTodos() {
        return todoRepository.findAll()
                .stream()
                .map(TodoDto::new)
                .collect(Collectors.toList());




    }
}
