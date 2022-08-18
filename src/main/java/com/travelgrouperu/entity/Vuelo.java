package com.travelgrouperu.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Table
@Entity(name = "vuelo")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Vuelo {
    @Id
    //Se le indica que el campo ID es Autonumerico
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
