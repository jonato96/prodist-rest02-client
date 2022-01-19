package com.programacion;

import com.programacion.db.PersonaClient;
import com.programacion.httpclient.HttpClient;
import com.programacion.jaxrsclient.JaxrsClient;
import com.programacion.resteasyclient.ProxyClient;
import com.programacion.resteasyclient.ServicioPersonaClient;

public class Principal {

    public static void main(String[] args) throws Exception {
        //Clientes

        //HttpClient cliente = new HttpClient();
        //JaxrsClient cliente = new JaxrsClient();
        ServicioPersonaClient cliente = ProxyClient.create();

        //Crear dos clientes
        PersonaClient nuevo  = new PersonaClient(3, "Joselo", "Cuenca");
        PersonaClient nuevo01  = new PersonaClient(4, "Perla", "Esmeraldas");
        cliente.create(nuevo);
        cliente.create(nuevo01);
        //Editar el cliente con id 3
        PersonaClient edit = new PersonaClient(3, "JoseloEditado", "GYE");
        cliente.update(3, edit);
        //Buscar un cliente
        PersonaClient persona = cliente.findById(2);
        System.out.printf("Id: %d Nombre: %s\n", persona.getId(), persona.getNombre());
        //Eliminar un cliente
        cliente.delete(4);
        //Obtener todos los clientes
        cliente.findAll()
                .forEach(s -> {
                    System.out.printf("[%d] Nombre: %s Direccion: %s\n"
                            , s.getId()
                            , s.getNombre()
                            , s.getDireccion());
                });
    }

}
