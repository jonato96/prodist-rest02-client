package com.programacion.jaxrsclient;

import com.programacion.db.PersonaClient;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class JaxrsClient {

    private static final String URL = "http://localhost:8080/rest02/personas";

    public void create(PersonaClient persona) throws Exception{
        Client client = ClientBuilder.newClient();
        Entity<PersonaClient> entity = Entity.entity(persona, MediaType.APPLICATION_JSON);
        client.target(URL)
                .request()
                .post(entity);
    }

    public PersonaClient findById(Integer id){
        Client client = ClientBuilder.newClient();
        PersonaClient obj = client.target(URL+"/"+id)
                .request(MediaType.APPLICATION_JSON)
                .get(PersonaClient.class);
        return obj;
    }

    public List<PersonaClient> findAll(){
        Client client = ClientBuilder.newClient();
        PersonaClient[] personas = client.target(URL)
                .request(MediaType.APPLICATION_JSON)
                .get(PersonaClient[].class);
        return Arrays.asList(personas);
    }

    public void update(Integer id, PersonaClient persona){
        Client client = ClientBuilder.newClient();
        Entity<PersonaClient> entity = Entity.entity(persona, MediaType.APPLICATION_JSON);
        client.target(URL+"/"+id)
                .request()
                .put(entity);
    }

    public void delete(Integer id){
        Client client = ClientBuilder.newClient();
        client.target(URL+"/"+id)
                .request()
                .delete();
    }

}
