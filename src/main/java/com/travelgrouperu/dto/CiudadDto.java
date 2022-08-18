package com.travelgrouperu.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CiudadDto {

    //Notación para especificar que el campo no puede venir vacio

    private String nombreCiudad;
    //Notación para indicar que el tamaño minimo debe ser 0

    private int cantidadAptos;

    private String urlImage;

}
