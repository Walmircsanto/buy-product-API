package org.compra.controller;


import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;
import org.compra.model.User;
import org.compra.service.UserService;

import java.net.URI;

@Path("/user")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserController {

    @Inject
    private UserService userService;



    @POST
    @Transactional
    public Response createUser(User user){
        this.userService.createUser(user);
        URI location = UriBuilder.fromResource(UserController.class)
                .path("{id}")
                .resolveTemplate("id", user.getId())
                .build();
        return Response.created(location).entity(user).build();

    }

    @Path("/{id}")
    @GET
    @Transactional
    public Response findById(@PathParam("id") Long id){

        return Response.ok(this.userService.getUser(id)).build();
    }

}
