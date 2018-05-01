package se.jaw.jaxrs.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
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


        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());

        User save = userService.saveUser(user);


        return Response.ok(save).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteUser(@PathParam("id") String id) {
        userService.deleteUser(id);
        return Response.ok().build();
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


}
