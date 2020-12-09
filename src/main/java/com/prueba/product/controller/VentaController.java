package com.prueba.product.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prueba.product.components.ErrorMessage;
import com.prueba.product.entity.Cliente;
import com.prueba.product.entity.Venta;
import com.prueba.product.service.VentaService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RestController
@RequestMapping(value = "/ventas")
public class VentaController {
    private static final Logger logger = LogManager.getLogger(VentaController.class);
    @Autowired
    private VentaService ventaService;

    @GetMapping
    public ResponseEntity<List<Venta>> listVentaIdCliente(@RequestParam(name = "clienteid", required = false) Integer clienteId){
        List<Venta> ventaIdClienteList = new ArrayList<>();
        if (null==clienteId){
            ventaIdClienteList = ventaService.listAllVenta();
            if (ventaIdClienteList.isEmpty()){
                logger.info("La consulta de todas las ventas no arrojo registros");
                return ResponseEntity.noContent().build();
            }
        }else{
            ventaIdClienteList = ventaService.findByIdCliente(Cliente.builder().idCliente(clienteId).build());
            if (ventaIdClienteList.isEmpty()){
                logger.error("No se encontró una venta con el nombre especificado");
                return ResponseEntity.notFound().build();
            }
        }
        logger.info("La consulta arrojo : {}",ventaIdClienteList);
        return ResponseEntity.ok(ventaIdClienteList);
    }

    @PostMapping
    public ResponseEntity<Venta> createVenta(@Valid @RequestBody Venta venta, BindingResult result){
        logger.info("Creando la venta : {}",venta);
        if (result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
        }
        logger.info("Producto creado");
        Venta ventaCreate = ventaService.createVenta(venta);
        return ResponseEntity.status(HttpStatus.CREATED).body(ventaCreate);
    }

    private String formatMessage(BindingResult result){
        List<Map<String,String>> errors = result.getFieldErrors().stream()
                .map(err ->{
                    Map<String,String> error = new HashMap<>();
                    error.put(err.getField(), err.getDefaultMessage());
                    return error;
                }).collect(Collectors.toList());
        ErrorMessage errorMessage = ErrorMessage.builder()
                .code("01")
                .messages(errors).build();

        ObjectMapper mapper = new ObjectMapper();
        String jsonString="";
        try{
            jsonString = mapper.writeValueAsString(errorMessage);
        }catch (JsonProcessingException e){
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return jsonString;
    }
}
