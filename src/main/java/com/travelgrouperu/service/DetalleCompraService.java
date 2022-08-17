package com.travelgrouperu.service;

import com.travelgrouperu.dto.DetalleCompraDto;
import com.travelgrouperu.entity.DetalleCompra;
import com.travelgrouperu.repository.DetalleCompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DetalleCompraService {

    @Autowired
    DetalleCompraRepository repo;

    public List<DetalleCompra> listaDetalleCompra() {
        return repo.findAll();
    }

    public Optional<DetalleCompra> getDetalleCompra(Integer idDetalleCompra) {
        return repo.findById(idDetalleCompra);
    }

    public void saveDetalleCompra(DetalleCompraDto detalleCompra) {
        DetalleCompra d = new DetalleCompra();
        d.setIdDetalle(detalleCompra.getIdDetalle());
        d.setOrigen(detalleCompra.getOrigen());
        d.setDestino(detalleCompra.getDestino());
        d.setFechaSalida(detalleCompra.getFechaSalida());
        d.setCantidadPersonas(detalleCompra.getCantidadPersonas());
        d.setTipoViaje(detalleCompra.getTipoViaje());
        d.setNombrePasajero(detalleCompra.getNombrePasajero());
        d.setCodigo(detalleCompra.getCodigo());
        d.setHora(detalleCompra.getHora());
        d.setPrecioTotal(detalleCompra.getPrecioTotal());

        repo.save(d);

    }

    public void deleteDetalleCompra(Integer idDetalleCompra) {
        repo.deleteById(idDetalleCompra);
    }
}
