package com.travelgrouperu.repository;

import com.travelgrouperu.entity.Ciudad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//Notación para indicar que es un repositorio
@Repository
public interface CiudadRepository extends JpaRepository<Ciudad, Integer> {
        // Con @Repository le indico los metodos principales select, create, update, delete

    //Convención sobre convicción
    //CrudRepository permite realizar busquedas por campo según la entidad
    Optional<Ciudad> findByNombreCiudad(String nombreCiudad);

    boolean existsByNombreCiudad(String nombreCiudad);

}