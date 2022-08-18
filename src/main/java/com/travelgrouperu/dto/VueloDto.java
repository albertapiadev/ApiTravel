package com.travelgrouperu.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Setter
@Getter
public class VueloDto {

    private Integer idVuelo;

    private String origen;

    private String destino;

    private String fechaSalida;

    private Integer cantidadPersonas;

    private String tipoViaje;

    private String nombrePasajero;

    private String codigo;

    private String hora;

    private String numeroTarjeta;

    private Integer cvv;

    private Double precioTotal;

}
