package com.travelgrouperu.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class CiudadDto {

    //Notación para especificar que el campo no puede venir vacio
    @NotBlank
    private String nombreCiudad;
    //Notación para indicar que el tamaño minimo debe ser 0
    @Min(0)
    private int cantidadAptos;

    public CiudadDto() {
    }

    public CiudadDto(String nombreCiudad, int cantidadAptos) {
        this.nombreCiudad = nombreCiudad;
        this.cantidadAptos = cantidadAptos;
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
