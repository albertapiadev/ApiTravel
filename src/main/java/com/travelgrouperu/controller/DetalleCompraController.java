package com.travelgrouperu.controller;

import com.travelgrouperu.dto.DetalleCompraDto;
import com.travelgrouperu.entity.DetalleCompra;
import com.travelgrouperu.service.DetalleCompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/detalleCompra")
@CrossOrigin(origins = "http://localhost:4200")
public class DetalleCompraController {

    @Autowired
    DetalleCompraService serv;

    @GetMapping ("/listaDetalleCompra")
    public ResponseEntity<List<DetalleCompra>> listaDetalleCompra(){

        List<DetalleCompra> detalleCompras = serv.listaDetalleCompra();
        return new ResponseEntity<List<DetalleCompra>>(detalleCompras, HttpStatus.OK);
    }

    /*@PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/crearDetalleCompra")
    public ResponseEntity<Object> crearDetalleCompra(@RequestBody DetalleCompraDto detalleCompra){

        Map<String, Object> errors = new LinkedHashMap<>();
        Map<String, Object> rtn = new LinkedHashMap<>();
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<DetalleCompraDto>> violations = validator.validate(detalleCompra);

        for (ConstraintViolation<DetalleCompraDto> violation : violations) {
            System.out.print(violation.getPropertyPath()+"-"+violation.getMessage());
            System.out.println("");
            errors.put(violation.getPropertyPath().toString(), violation.getMessage());
        }
        if(!errors.isEmpty()){
            rtn.put("message", "error al registrar");
            rtn.put("content", errors);

            return new ResponseEntity<Object>(rtn, HttpStatus.BAD_REQUEST);
        }
        serv.saveDetalleCompra(detalleCompra);
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
        rtn.put("message", "Detalle de Compra registrado correctamente");
        rtn.put("content", rptaRR);
        return new ResponseEntity(rtn, HttpStatus.OK);

    }*/
}
