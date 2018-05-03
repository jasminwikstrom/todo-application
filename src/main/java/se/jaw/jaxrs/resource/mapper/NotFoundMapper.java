package se.jaw.jaxrs.resource.mapper;

import se.jaw.jaxrs.model.NotFoundException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.Collections;

import static javax.ws.rs.core.Response.Status.NOT_FOUND;

@Provider
public class NotFoundMapper implements ExceptionMapper<NotFoundException> {

    @Override
    public Response toResponse(NotFoundException exception) {
        return Response.status(NOT_FOUND)
                .entity(Collections.singletonMap("error", exception.getMessage()))
                .build();
    }
}
