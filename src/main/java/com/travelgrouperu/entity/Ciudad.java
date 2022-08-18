package com.travelgrouperu.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

//Notación para indicar que es una entidad
@Entity
//Tabla que corresponde a esta entidad
@Table(name = "ciudad")
@Getter
@Setter
public class Ciudad {

    //Llave primaria de la tabla
    @Id
    //Se le indica que el campo ID es Autonumerico
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCiudad;
    private String nombreCiudad;
    private int cantidadAptos;
    private String urlImage;
    public Ciudad() {
    }

    /*
    Constructor con parametros
     */
    public Ciudad(String nombreCiudad, int cantidadAptos, String urlImage) {
        this.nombreCiudad = nombreCiudad;
        this.cantidadAptos = cantidadAptos;
        this.urlImage=urlImage;
    }

}