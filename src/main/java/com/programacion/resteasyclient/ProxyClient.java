package com.programacion.resteasyclient;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

public class ProxyClient {
    public static final String URL = "http://localhost:8080/rest02";
    private ProxyClient(){
    }
    public static ServicioPersonaClient create(){
        ResteasyClientBuilder builder = (ResteasyClientBuilder) ResteasyClientBuilder.newBuilder();
        ResteasyClient client = builder.build();
        ResteasyWebTarget target = client.target(URL);
        return target.proxy(ServicioPersonaClient.class);
    }

}
