package com.travelgrouperu.service;

import com.travelgrouperu.dto.response.DetalleCompraDtoResponse;

import java.util.List;

public interface DetalleCompraService {

    public DetalleCompraDtoResponse obtenerDetalleCompraId(Integer id);
    public List<DetalleCompraDtoResponse> listarDetalleCompra();
}
