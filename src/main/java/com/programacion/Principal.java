package com.programacion;

import com.programacion.db.PersonaClient;
import com.programacion.httpclient.HttpClient;

public class Principal {

    public static void main(String[] args) throws Exception {
        HttpClient cliente = new HttpClient();

        PersonaClient nuevo  = new PersonaClient(3, "Joselo", "Cuenca");
        PersonaClient nuevo01  = new PersonaClient(4, "Perla", "Esmeraldas");
        cliente.create(nuevo);
        cliente.create(nuevo01);

        PersonaClient edit = new PersonaClient(3, "JoseloEditado", "GYE");
        cliente.update(3, edit);

        PersonaClient persona = cliente.findById(2);
        System.out.printf("Id: %d Nombre: %s\n", persona.getId(), persona.getNombre());

        cliente.delete(4);

        cliente.findAll()
                .forEach(s -> {
                    System.out.printf("[%d] Nombre: %s Direccion: %s\n"
                            , s.getId()
                            , s.getNombre()
                            , s.getDireccion());
                });
    }

}
