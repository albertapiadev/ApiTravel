package com.travelgrouperu.controller;

import com.travelgrouperu.dto.CiudadDto;
import com.travelgrouperu.dto.Mensaje;
import com.travelgrouperu.entity.Ciudad;
import com.travelgrouperu.service.CiudadService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//Notación para indicar que es un controlador de tipo Rest
@RestController
//Notación para indicar el contexto de nuestros endpoint es decir /ciudad/nombreServicio
@RequestMapping("/ciudad")
//URL que permitimos que consuman nuestras APIS
//En caso de querer permitir todos los origentes ponemos en lugar de la URL un *
@CrossOrigin(origins = "http://localhost:4200")
public class CiudadController {

    /*El nombre de las ciudads es unico,
    en la creación y actualizacón se hace la validación*/

    //Inyección de dependencias
    @Autowired
    CiudadService ciudadService;

    //Se le indica el tipo de petición asi como el nombre del servicio
    @GetMapping("/listaCiudad")
    public ResponseEntity<List<Ciudad>> listaCiudads(){

        List<Ciudad> ciudads = ciudadService.listaCiudad();
        return new ResponseEntity<List<Ciudad>>(ciudads, HttpStatus.OK);
    }

    @GetMapping("/detalleCiudad/{idCiudad}")
    public ResponseEntity<Ciudad> ciudadById(@PathVariable("idCiudad") int idCiudad){

        if (!ciudadService.existsByIdCiudad(idCiudad))
            return new ResponseEntity(new Mensaje("No existe la ciudad"), HttpStatus.NOT_FOUND);

        Ciudad ciudad = ciudadService.getCiudad(idCiudad).get();
        return new ResponseEntity(ciudad, HttpStatus.OK);
    }

    @GetMapping("/detalleNombre/{nombreCiudad}")
    public ResponseEntity<Ciudad> ciudadByNombre(@PathVariable("nombreCiudad") String nombreCiudad){

        if (!ciudadService.existsByNombreCiudad(nombreCiudad))
            return new ResponseEntity(new Mensaje("No existe la ciudad"), HttpStatus.NOT_FOUND);

        Ciudad ciudad = ciudadService.getByNombreCiudad(nombreCiudad).get();
        return new ResponseEntity(ciudad, HttpStatus.OK);
    }

    //Con el ? le decimos que no devulve ningún tipo de dato
    //El body va a ser un JSON
    //Aqui se usa el apache commons lang
    // El import de StringUtils es import org.apache.commons.lang3.StringUtils;
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/crearCiudad")
    public ResponseEntity<?> creaCiudad(@RequestBody CiudadDto ciudadDto){

        if(StringUtils.isBlank(ciudadDto.getNombreCiudad()))
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);

        if(ciudadDto.getCantidadAptos()<0 || (Integer) ciudadDto.getCantidadAptos() == null)
            return new ResponseEntity(new Mensaje("La cantidad de aptos debe ser mayor a 0"), HttpStatus.BAD_REQUEST);

        if(ciudadService.existsByNombreCiudad(ciudadDto.getNombreCiudad()))
            return new ResponseEntity(new Mensaje("Ya existe una ciudad con ese nombre"), HttpStatus.BAD_REQUEST);


        Ciudad ciudad = new Ciudad(ciudadDto.getNombreCiudad(), ciudadDto.getCantidadAptos(),ciudadDto.getUrlImage());
        ciudadService.saveCiudad(ciudad);
        return new ResponseEntity(new Mensaje("Ciudad creada"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/actualizarCiudad/{idCiudad}")
    public ResponseEntity<?> actualizarCiudad(@PathVariable("idCiudad") int idCiudad, @RequestBody CiudadDto ciudadDto){

        if (!ciudadService.existsByIdCiudad(idCiudad))
        return new ResponseEntity(new Mensaje("No existe la ciudad"), HttpStatus.NOT_FOUND);

        if (ciudadService.existsByNombreCiudad(ciudadDto.getNombreCiudad())
                && ciudadService.getByNombreCiudad(ciudadDto.getNombreCiudad()).get().getIdCiudad() != idCiudad)
            return new ResponseEntity(new Mensaje("El nombre de la ciudad ya existe"), HttpStatus.NOT_FOUND);

        if(StringUtils.isBlank(ciudadDto.getNombreCiudad()))
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);

        if(ciudadDto.getCantidadAptos()<0 || (Integer) ciudadDto.getCantidadAptos() == null)
            return new ResponseEntity(new Mensaje("La cantidad de aptos debe ser mayor a 0"), HttpStatus.BAD_REQUEST);

        Ciudad ciudad = ciudadService.getCiudad(idCiudad).get();
        ciudad.setNombreCiudad(ciudadDto.getNombreCiudad());
        ciudad.setCantidadAptos(ciudadDto.getCantidadAptos());
        ciudad.setUrlImage(ciudadDto.getUrlImage());
        ciudadService.saveCiudad(ciudad);
        return new ResponseEntity(new Mensaje("Ciudad actualizada"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/borrarCiudad/{idCiudad}")
    public ResponseEntity<?> borrarCiudad(@PathVariable("idCiudad") int idCiudad){
        if (!ciudadService.existsByIdCiudad(idCiudad))
            return new ResponseEntity(new Mensaje("No existe la ciudad"), HttpStatus.NOT_FOUND);
        ciudadService.deleteCiudad(idCiudad);
        return new ResponseEntity(new Mensaje("Ciudad eliminada"), HttpStatus.OK);
    }

}
