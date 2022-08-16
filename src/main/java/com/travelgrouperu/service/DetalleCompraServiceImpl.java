package com.travelgrouperu.service;

import com.travelgrouperu.dto.response.DetalleCompraDtoResponse;
import com.travelgrouperu.entity.DetalleCompra;
import com.travelgrouperu.repository.DetalleCompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DetalleCompraServiceImpl implements DetalleCompraService {

    @Autowired
    public DetalleCompraRepository repository;

    @Override
    public DetalleCompraDtoResponse obtenerDetalleCompraId(Integer id) {

        DetalleCompra detalleCompra = repository.findById(id).orElse(null);
        DetalleCompraDtoResponse dc = new DetalleCompraDtoResponse();
        dc.setIdDetalleDto(detalleCompra.getIdDetalle());
        dc.setOrigenDto(detalleCompra.getOrigen());
        dc.setDestinoDto(detalleCompra.getDestino());
        dc.setFechaSalidaDto(detalleCompra.getFechaSalida());
        dc.setCantidadPersonasDto(detalleCompra.getCantidadPersonas());
        dc.setTipoViajeDto(detalleCompra.getTipoViaje());
        dc.setNombrePasajeroDto(detalleCompra.getNombrePasajero());
        dc.setCodigoDto(detalleCompra.getCodigo());
        dc.setHoraDto(detalleCompra.getHora());
        dc.setPrecioDto(detalleCompra.getPrecio());

        return dc;
    }

    @Override
    public List<DetalleCompraDtoResponse> listarDetalleCompra() {
        List<DetalleCompraDtoResponse> lista = new ArrayList<>(0);
        DetalleCompraDtoResponse dc = null;
        for (DetalleCompra detalleCompra : repository.findAll()) {
            dc = new DetalleCompraDtoResponse();
            dc.setIdDetalleDto(detalleCompra.getIdDetalle());
            dc.setOrigenDto(detalleCompra.getOrigen());
            dc.setDestinoDto(detalleCompra.getDestino());
            dc.setFechaSalidaDto(detalleCompra.getFechaSalida());
            dc.setCantidadPersonasDto(detalleCompra.getCantidadPersonas());
            dc.setTipoViajeDto(detalleCompra.getTipoViaje());
            dc.setNombrePasajeroDto(detalleCompra.getNombrePasajero());
            dc.setCodigoDto(detalleCompra.getCodigo());
            dc.setHoraDto(detalleCompra.getHora());
            dc.setPrecioDto(detalleCompra.getPrecio());
            lista.add(dc);
        }
        return lista;
    }
}

