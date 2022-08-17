package com.travelgrouperu.controller;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.travelgrouperu.dto.CiudadDto;
import com.travelgrouperu.dto.Mensaje;
import com.travelgrouperu.entity.Ciudad;
import com.travelgrouperu.entity.Vuelo;
import com.travelgrouperu.service.CiudadService;
import com.travelgrouperu.service.VueloService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.travelgrouperu.dto.VueloDto;

import javax.validation.ConstraintViolation;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
//Notación para indicar el contexto de nuestros endpoint es decir /ciudad/nombreServicio
@RequestMapping("/vuelo")
//URL que permitimos que consuman nuestras APIS
//En caso de querer permitir todos los origentes ponemos en lugar de la URL un *
@CrossOrigin(origins = "http://localhost:4200")
public class VueloController {

    /*El nombre de las ciudads es unico,
    en la creación y actualizacón se hace la validación*/

    //Inyección de dependencias
    @Autowired
    VueloService serv;

    //Se le indica el tipo de petición asi como el nombre del servicio
    @GetMapping("/listaVuelo")
    public ResponseEntity<List<Vuelo>> listaVuelos(){

        List<Vuelo> vuelos = serv.listaVuelo();
        return new ResponseEntity<List<Vuelo>>(vuelos, HttpStatus.OK);
    }

    /*@GetMapping("/detalleVuelo/{idVuelo}")
    public ResponseEntity<Ciudad> ciudadById(@PathVariable("idVuelo") int idVuelo){

        //if (!serv.existsByIdCiudad(idCiudad))
         //   return new ResponseEntity(new Mensaje("No existe la ciudad"), HttpStatus.NOT_FOUND);

        Vuelo vuelo = serv.getVuelo(idVuelo).get();
        return new ResponseEntity(vuelo, HttpStatus.OK);
    }*/

    /*@GetMapping("/detalleNombre/{nombreCiudad}")
    public ResponseEntity<Ciudad> ciudadByNombre(@PathVariable("nombreCiudad") String nombreCiudad){

        if (!ciudadService.existsByNombreCiudad(nombreCiudad))
            return new ResponseEntity(new Mensaje("No existe la ciudad"), HttpStatus.NOT_FOUND);

        Ciudad ciudad = ciudadService.getByNombreCiudad(nombreCiudad).get();
        return new ResponseEntity(ciudad, HttpStatus.OK);
    }*/

    //Con el ? le decimos que no devulve ningún tipo de dato
    //El body va a ser un JSON
    //Aqui se usa el apache commons lang
    // El import de StringUtils es import org.apache.commons.lang3.StringUtils;
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/crearVuelo")
    public ResponseEntity<Object> creaVuelo(@RequestBody VueloDto vuelo){

        Map<String, Object> errors = new LinkedHashMap<>();
        Map<String, Object> rtn = new LinkedHashMap<>();
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<VueloDto>> violations = validator.validate(vuelo);


        for (ConstraintViolation<VueloDto> violation : violations) {
            System.out.print(violation.getPropertyPath()+"-"+violation.getMessage());
            System.out.println("");
            errors.put(violation.getPropertyPath().toString(), violation.getMessage());
        }
        if(!errors.isEmpty()) {
            rtn.put("message", "error al registrar");
            rtn.put("content", errors);

            return new ResponseEntity<>(rtn,HttpStatus.NOT_FOUND);
        }

        //Ciudad ciudad = new Ciudad(ciudadDto.getNombreCiudad(), ciudadDto.getCantidadAptos());
        serv.saveVuelo(vuelo);
        class rptaR{
            private boolean rpta;

            public boolean isRpta() {
                return rpta;
            }

            public void setRpta(boolean rpta) {
                this.rpta = rpta;
            }
        }
        rptaR rptaRR = new rptaR();
        rptaRR.setRpta(true);
        rtn.put("message", "Vuelo creada");
        rtn.put("content", rptaRR);
        return new ResponseEntity(rtn, HttpStatus.OK);
    }

   /* @PreAuthorize("hasRole('ADMIN')")
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
        ciudadService.saveCiudad(ciudad);
        return new ResponseEntity(new Mensaje("Ciudad actualizada"), HttpStatus.OK);
    }*/

    /*@PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/borrarCiudad/{idCiudad}")
    public ResponseEntity<?> borrarCiudad(@PathVariable("idCiudad") int idCiudad){
        if (!ciudadService.existsByIdCiudad(idCiudad))
            return new ResponseEntity(new Mensaje("No existe la ciudad"), HttpStatus.NOT_FOUND);
        ciudadService.deleteCiudad(idCiudad);
        return new ResponseEntity(new Mensaje("Ciudad eliminada"), HttpStatus.OK);
    }*/
}
