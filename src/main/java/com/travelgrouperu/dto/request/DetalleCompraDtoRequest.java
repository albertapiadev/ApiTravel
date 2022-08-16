package com.travelgrouperu.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class DetalleCompraDtoRequest {

    private int idDetalleDto;
    private String origenDto;
    private String destinoDto;
    private Date fechaSalidaDto;
    private int cantidadPersonasDto;
    private String tipoViajeDto;
    private String nombrePasajeroDto;
    private String codigoDto;
    private String horaDto;
    private Double precioDto;
}
