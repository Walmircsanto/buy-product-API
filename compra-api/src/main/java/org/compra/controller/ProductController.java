package org.compra.controller;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;
import org.compra.model.Product;
import org.compra.service.ProductService;

import java.net.URI;

@Path("/product")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProductController {

    @Inject
    ProductService productService;


    @POST
    @Transactional
    public Response createdProduct(Product product){
        this.productService.createdProduct(product);
        URI location = UriBuilder.fromResource(ProductController.class)
                .path("{id}")
                .resolveTemplate("id", product.getId())
                .build();
        return Response.created(location).entity(product).build();

    }


    @GET
    @Path("/{id}")
    @Transactional
    public Response getProductById(@PathParam("id") Long id){
        return Response.ok(this.productService.findById(id)).build();
    }
}
