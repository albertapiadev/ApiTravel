package com.travelgrouperu.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Getter
@Setter
public class DetalleCompraDto {

    private Integer idDetalle;
    private String origen;
    private String destino;
    private String fechaSalida;
    private Integer cantidadPersonas;
    private String tipoViaje;
    private String nombrePasajero;
    private String codigo;
    private String hora;
    private Double precioTotal;

    private Integer idVuelo;
}
