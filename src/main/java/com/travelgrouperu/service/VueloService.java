package com.travelgrouperu.service;

import com.travelgrouperu.entity.Ciudad;
import com.travelgrouperu.entity.Vuelo;
import com.travelgrouperu.dto.VueloDto;
import com.travelgrouperu.repository.VueloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class VueloService {

    @Autowired
    VueloRepository repo;

    public List<Vuelo> listaVuelo() {
        return repo.findAll();
    }

    public Optional<Vuelo> getVuelo(Integer idVuelo) {
        return repo.findById(idVuelo);
    }

   /* public Optional<Vuelo> getByNombreCiudad(String nombreCiudad){
        return ciudadRepository.findByNombreCiudad(nombreCiudad);
    }*/

    public void saveVuelo(VueloDto vuelo) {
        Vuelo v = new Vuelo();
        v.setIdVuelo(vuelo.getIdVuelo());
        v.setOrigen(vuelo.getOrigen());
        v.setDestino(vuelo.getDestino());
        v.setFechaSalida(vuelo.getFechaSalida());
        v.setCantidadPersonas(vuelo.getCantidadPersonas());
        v.setTipoViaje(vuelo.getTipoViaje());
        v.setNombrePasajero(vuelo.getNombrePasajero());
        v.setCodigo(vuelo.getCodigo());
        v.setHora(vuelo.getHora());
        v.setNumeroTarjeta(vuelo.getNumeroTarjeta());
        v.setCvv(vuelo.getCvv());
        v.setPrecioTotal(vuelo.getPrecioTotal());

        repo.save(v);
    }

    public void deleteVuelo(Integer idVuelo) {
        repo.deleteById(idVuelo);
    }

   /* public boolean existsByIdVuelo(int idCiudad){
        return repo.existsById(idCiudad);
    }*/

    /*public boolean existsByNombreCiudad(String nombreCiudad){
        return repo.existsByNombreCiudad(nombreCiudad);
    }*/

}
