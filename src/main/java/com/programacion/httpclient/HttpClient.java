package com.programacion.httpclient;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.programacion.db.PersonaClient;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class HttpClient {

    public static final String URL = "http://localhost:8080/rest02/personas";
    ObjectMapper mapper = new ObjectMapper();

    public void create(PersonaClient persona) throws Exception {
        String txtJson = mapper.writeValueAsString(persona);
        StringEntity entity = new StringEntity(txtJson);

        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost(URL);
        post.addHeader(HttpHeaders.ACCEPT, "application/json");
        post.addHeader(HttpHeaders.CONTENT_TYPE, "application/json");
        post.setEntity(entity);

        CloseableHttpResponse response = client.execute(post);
        int statusCode = response.getStatusLine().getStatusCode();
        System.out.println("CREADO, el codigo es "+statusCode);
    }

    public List<PersonaClient> findAll() throws Exception{
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet get = new HttpGet(URL);
        get.addHeader(HttpHeaders.ACCEPT, "application/json");
        CloseableHttpResponse response = client.execute(get);
        int statusCode = response.getStatusLine().getStatusCode();
        if(statusCode == HttpStatus.SC_OK){
            String txt = EntityUtils.toString(response.getEntity());
            PersonaClient[] personas = mapper.readValue(txt, PersonaClient[].class);
            return Arrays.asList(personas);
        }
        else{
            return Collections.emptyList();
        }
    }

    public PersonaClient findById(Integer id) throws Exception{
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet get = new HttpGet(URL+"/"+id);
        get.addHeader(HttpHeaders.ACCEPT, "application/json");
        CloseableHttpResponse response  = client.execute(get);
        int statusCode = response.getStatusLine().getStatusCode();
        if(statusCode == HttpStatus.SC_OK){
            String txt = EntityUtils.toString(response.getEntity());
            PersonaClient persona = mapper.readValue(txt, PersonaClient.class);
            return persona;
        }
        else{
            return new PersonaClient();
        }
    }

    public void update(Integer id, PersonaClient persona) throws Exception {
        String txtJson = mapper.writeValueAsString(persona);
        StringEntity entity = new StringEntity(txtJson);

        CloseableHttpClient client = HttpClients.createDefault();
        HttpPut put = new HttpPut(URL+"/"+id);
        put.setHeader(HttpHeaders.ACCEPT, "application/json");
        put.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
        put.setEntity(entity);

        CloseableHttpResponse response = client.execute(put);
        int statusCode = response.getStatusLine().getStatusCode();
        System.out.println("ACTUALIZADO, el codigo es "+statusCode);
    }

    public void delete(Integer id) throws Exception{
        CloseableHttpClient client = HttpClients.createDefault();
        HttpDelete delete = new HttpDelete(URL+"/"+id);
        CloseableHttpResponse response = client.execute(delete);
        int statusCode = response.getStatusLine().getStatusCode();
        System.out.println(statusCode == HttpStatus.SC_NO_CONTENT ? "Eliminado satisfactoriamente, code: "+statusCode : "Error contacte al admin");
    }
}
