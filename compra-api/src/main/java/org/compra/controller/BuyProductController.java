package org.compra.controller;


import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.compra.service.BuyProductUser;

@Path("/buy-product")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BuyProductController {

    @Inject
    BuyProductUser buyProductUser;



    @POST
    @Transactional
    @Path("/{idUser}/{idProduct}")
    public Response buyProduct(@PathParam("idUser") Long idUser, @PathParam("idProduct") Long  idProduct) {
        this.buyProductUser.buyProduct(idUser, idProduct);
        return Response.ok().build();

    }
}
