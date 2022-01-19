package com.programacion.resteasyclient;

import com.programacion.db.PersonaClient;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/personas")
public interface ServicioPersonaClient {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    List<PersonaClient> findAll();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    PersonaClient findById(@PathParam("id") Integer id);

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    Response create(PersonaClient p);

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    Response update(@PathParam("id") Integer id, PersonaClient p);

    @DELETE
    @Path("/{id}")
    Response delete(@PathParam("id") Integer id);
}
