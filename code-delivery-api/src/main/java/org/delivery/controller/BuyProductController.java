package org.delivery.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.delivery.service.BuyProductService;

@Path("/buy-products")
@Produces(MediaType.APPLICATION_JSON)
public class BuyProductController {

    @Inject
    BuyProductService buyProductService;


    @GET
    public Response getAllBuyProducts() {
        return Response.ok().entity(buyProductService.findAll()).build();
    }
}
