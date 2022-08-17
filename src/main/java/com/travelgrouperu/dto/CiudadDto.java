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
    @NotBlank
    private String urlImage;
    public CiudadDto() {
    }

    public CiudadDto(String nombreCiudad, int cantidadAptos,String urlImage) {
        this.nombreCiudad = nombreCiudad;
        this.cantidadAptos = cantidadAptos;
        this.urlImage=urlImage;
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

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }
}
