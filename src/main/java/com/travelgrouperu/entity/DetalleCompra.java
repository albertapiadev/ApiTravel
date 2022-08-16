package com.travelgrouperu.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "detalle_compra")
@Table
public class DetalleCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idDetalle;
    private String origen;
    private String destino;
    private Date fechaSalida;
    private int cantidadPersonas;
    private String tipoViaje;
    private String nombrePasajero;
    private String codigo;
    private String hora;
    private Double precio;

}
