package com.travelgrouperu.service;

import com.travelgrouperu.entity.Ciudad;
import com.travelgrouperu.repository.CiudadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

//Notación para indicar que es un servicio
@Service
//Asegura que toda la data requerida este segura hasta que la transacción termine
//Recomiendo leer acerca de esta notación (es un mundo completo jeje) 
@Transactional
public class CiudadService {

    //Inyección de dependecias (crea una instancia cuando lo requiera)
    @Autowired
    CiudadRepository ciudadRepository;

    //Por defecto el repositorio al extender de JPA trae el metodo por defecto
    public List<Ciudad> listaCiudad(){
        return  ciudadRepository.findAll();
    }

    public Optional<Ciudad> getCiudad(int idCiudad){
        return  ciudadRepository.findById(idCiudad);
    }

    public Optional<Ciudad> getByNombreCiudad(String nombreCiudad){
        return ciudadRepository.findByNombreCiudad(nombreCiudad);
    }

    public void saveCiudad(Ciudad ciudad){
        ciudadRepository.save(ciudad);
    }

    public void deleteCiudad(int idCiudad){
        ciudadRepository.deleteById(idCiudad);
    }

    public boolean existsByIdCiudad(int idCiudad){
        return ciudadRepository.existsById(idCiudad);
    }

    public boolean existsByNombreCiudad(String nombreCiudad){
        return ciudadRepository.existsByNombreCiudad(nombreCiudad);
    }

}