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
import javax.ws.rs.core.Response;
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
    public TodoDto addTodo(TodoDto todoDto) {
        final Todo todo = new Todo(
                todoDto.getUserId() != null ? Long.valueOf(todoDto.getUserId()) : null,
                todoDto.getImportance() != null ? todoDto.getImportance().name() : null,
                todoDto.getDescription());

        return todoService.saveTodo(todo);
    }

    @DELETE
    @Path("/{id}")
    public Response deleteTodo(@PathParam("id") String id) {
        todoService.deleteTodo(id);
        return Response.ok().build();
    }

    @GET
    public List<TodoDto> getTodos(
            @QueryParam("userId") String userId,
            @QueryParam("importance") ImportanceType importanceType) {
        return todoService.getTodos(userId, importanceType);
    }

    @GET
    @Path("/{id}")
    public TodoDto getTodo(@PathParam("id") String id) {
        return todoService.getTodo(id)
                .orElseThrow(() -> new NotFoundException("Not found"));
    }

    // users/user-id/todos GET --> All todos
    // users/user-id/todos POST --> Add new TODO to user
    // users/user-id/todos?priority=1000 GET --> All todos priority

    @PUT
    @Path("{id}")
    public Todo todoAssignment(@PathParam("id") String id, String userId) {
        return todoService.todoAssignment(id, userId);
    }


}





