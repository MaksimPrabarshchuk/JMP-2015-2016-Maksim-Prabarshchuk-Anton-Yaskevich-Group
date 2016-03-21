package com.epam.resource;

import com.epam.dao.UserDAO;
import com.epam.model.User;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.LongParam;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {
    private UserDAO userDAO;

    public UserResource(UserDAO employeeDAO) {
        this.userDAO = employeeDAO;
    }

    @GET
    @UnitOfWork
    public List<User> getAll() {
        return userDAO.findAll();
    }

    @GET
    @Path("/{id}")
    @UnitOfWork
    public Response read(@PathParam("id") LongParam id) {
        Optional<User> user = userDAO.read(id.get());
        if (user.isPresent()) {
            return Response
                    .ok(user.get())
                    .build();
        } else {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .build();
        }
    }

    @POST
    @UnitOfWork
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_HTML)
    public Response create(User user) {
        Optional<User> created = userDAO.create(user);
        if (created.isPresent()) {
            return Response
                    .status(Response.Status.CREATED)
                    .entity(String.format(
                            "A new podcast/resource has been created at /users/%d",
                            created.get().getId()))
                    .header("Location", String.format(
                            "http://localhost:8080/users/%d",
                            created.get().getId()))
                    .build();
        } else {
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .build();
        }

    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_HTML)
    public Response update(@PathParam("id") Long id, User user) {
        Optional<User> toUpdate = userDAO.read(id);
        if (toUpdate.isPresent()) {
            userDAO.update(user);
            return Response
                    .status(Response.Status.OK)
                    .entity("The user you specified has been fully updated AT THE LOCATION you specified")
                    .header("Location", String.format(
                            "http://localhost:8080/users/%s",
                            String.valueOf(id)))
                    .build();
        } else {
            return Response
                    .status(Response.Status.NO_CONTENT)
                    .build();
        }
    }

    @DELETE
    @Path("{id}")
    @Produces({MediaType.TEXT_HTML})
    public Response delete(@PathParam("id") Long id) {
        Optional<User> toDelete = userDAO.read(id);
        toDelete.ifPresent(user -> userDAO.delete(user));
        return Response.status(Response.Status.NO_CONTENT)
                .entity("User was successfully removed from database")
                .build();
    }
}
