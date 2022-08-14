package com.travelgrouperu.entity;

import javax.persistence.*;

//Notación para indicar que es una entidad
@Entity
//Tabla que corresponde a esta entidad
@Table(name = "ciudad")
public class Ciudad {

    //Llave primaria de la tabla
    @Id
    //Se le indica que el campo ID es Autonumerico
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCiudad;
    private String nombreCiudad;
    private int cantidadAptos;

    public Ciudad() {
    }

    /*
    Constructor con parametros
     */
    public Ciudad(String nombreCiudad, int cantidadAptos) {
        this.nombreCiudad = nombreCiudad;
        this.cantidadAptos = cantidadAptos;
    }

    public int getIdCiudad() {
        return idCiudad;
    }

    public void setIdCiudad(int idCiudad) {
        this.idCiudad = idCiudad;
    }

    public String getNombreCiudad() {
        return nombreCiudad;
    }

    public void setNombreCiudad(String nombreCiudad) {
        this.nombreCiudad = nombreCiudad;
    }

    public int getCantidadAptos() {
        return cantidadAptos;
    }

    public void setCantidadAptos(int cantidadAptos) {
        this.cantidadAptos = cantidadAptos;
    }
}