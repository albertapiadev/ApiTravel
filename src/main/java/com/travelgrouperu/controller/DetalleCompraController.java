package com.travelgrouperu.controller;

import com.travelgrouperu.dto.response.DetalleCompraDtoResponse;
import com.travelgrouperu.service.DetalleCompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/detalleCompra")
public class DetalleCompraController {

    @Autowired
    private DetalleCompraService service;

    @GetMapping("/listar")
    public ResponseEntity<List<DetalleCompraDtoResponse>> listarDetalleCompra(){
        return new ResponseEntity<List<DetalleCompraDtoResponse>>(service.listarDetalleCompra(), HttpStatus.CREATED);
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<DetalleCompraDtoResponse> listarporId(Integer id) {
        DetalleCompraDtoResponse dc = service.obtenerDetalleCompraId(id);
        if (dc != null)
            return new ResponseEntity<DetalleCompraDtoResponse>(dc, HttpStatus.OK);
        return new ResponseEntity<DetalleCompraDtoResponse>(HttpStatus.NOT_FOUND);
    }
}
