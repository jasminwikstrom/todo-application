package se.jaw.jaxrs.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.jaw.jaxrs.model.ImportanceType;
import se.jaw.jaxrs.model.TodoDto;

import se.jaw.jaxrs.persistence.entity.Todo;
import se.jaw.jaxrs.service.TodoService;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import java.util.List;


@Path("/todos")
@Produces(MediaType.APPLICATION_JSON)
@Component
public class TodoResource {

    @Context
    private UriInfo uriInfo;

    @Autowired
    private TodoService todoService;


     @POST
     public TodoDto addTodo(TodoDto todoDto)
     {
         final Todo todo = new Todo(
                 todoDto.getUserId() != null ? Long.valueOf(todoDto.getUserId()) : null,
                 todoDto.getDescription(),
                 todoDto.getImportance().name());

         return todoService.saveTodo(todo);
     }
    @GET
    public List<TodoDto> getTodos(
            @QueryParam("userId") String userId,
            @QueryParam("importance") ImportanceType importanceType) {
        return todoService.getTodos(userId, importanceType);
    }

    @GET
    @Path("/{id}")
    public TodoDto getTodo(
            @PathParam("id") String id) {

        return todoService.getTodo(id)
                .orElseThrow(() -> new NotFoundException("Not found"));
    }


}





