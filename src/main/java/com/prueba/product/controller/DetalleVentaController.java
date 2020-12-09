package com.prueba.product.controller;

import com.prueba.product.entity.DetalleVenta;
import com.prueba.product.service.DetalleVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/detallventas")

public class DetalleVentaController {

    @Autowired
    private DetalleVentaService detalleVentaService;

    /*@GetMapping
    public ResponseEntity<DetalleVenta> getDetalleVenta(int id){

    }*/

    @GetMapping(value = "/{id}")
    public ResponseEntity<DetalleVenta> pruebaDV(@PathVariable("id") int id){
        DetalleVenta detalleVentadb = detalleVentaService.findByIdVenta(id);
        if (null==detalleVentadb){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(detalleVentadb);
    }

}
