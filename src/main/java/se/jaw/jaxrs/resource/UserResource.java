package se.jaw.jaxrs.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.jaw.jaxrs.model.BadUserInputException;
import se.jaw.jaxrs.model.UserDto;
import se.jaw.jaxrs.persistence.entity.User;
import se.jaw.jaxrs.service.UserService;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Component
public class UserResource {

    @Context
    private UriInfo uriInfo;

    @Autowired
    private UserService userService;

    @POST
    public Response addUser(UserDto userDto) {
        if (userDto.getFirstName().contains("Jasmin")) {
            throw new BadUserInputException("Bad username");
        }


        User user = new User();
        user.setFirstName(userDto.getFirstName());


        User save = userService.saveUser(user);

        // 2
        // URI location = uriInfo.getAbsolutePathBuilder().path(UserResource.class, "getUser").build(result.getId());

        return Response.ok(save).build();
    }

    @GET
    @Path("/{id}")
    public UserDto getUser(@PathParam("id") String id) {

        return userService.getUser(id)
                .orElseThrow(() -> new NotFoundException("Not found"));
    }

    @GET
    public List<UserDto> getUsers() {
        return userService.getUsers();
    }

    // users/1001/
    // note/{noteId}
    //@POST
//    @Path("{id}")
//    public UserDto addNote(Note note) {
//    }


}
