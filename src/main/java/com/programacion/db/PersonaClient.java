package com.programacion.db;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class PersonaClient {
    @Getter
    @Setter
    private Integer id;
    @Getter
    @Setter
    private String nombre;
    @Getter
    @Setter
    private String direccion;
}
