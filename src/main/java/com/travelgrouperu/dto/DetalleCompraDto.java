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
    @NotBlank
    private String origen;
    @NotBlank
    private String destino;
    @NotBlank
    private Date fechaSalida;
    @Min(1)
    private Integer cantidadPersonas;
    @NotBlank
    private String tipoViaje;
    @NotBlank
    private String nombrePasajero;
    @NotBlank
    private String codigo;
    @NotBlank
    private String hora;
    @NotBlank
    private Double precioTotal;


}
