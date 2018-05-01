package se.jaw.jaxrs.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.jaw.jaxrs.model.TodoDto;

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

    @GET
    public List<TodoDto> getTodos() {
        return todoService.getTodos();
    }

    @GET
    @Path("/{id}")
    public TodoDto getTodo(@PathParam("id") String id) {

        return todoService.getTodos(id)
                .orElseThrow(() -> new NotFoundException("Not found"));
    }


}





