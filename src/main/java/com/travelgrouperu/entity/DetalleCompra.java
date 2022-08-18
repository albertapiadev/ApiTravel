package com.travelgrouperu.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table
@Entity(name = "detallecompra")
public class DetalleCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
