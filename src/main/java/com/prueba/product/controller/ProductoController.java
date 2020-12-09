package com.prueba.product.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prueba.product.components.ErrorMessage;
import com.prueba.product.entity.Producto;
import com.prueba.product.service.ProductoService;
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
@RequestMapping(value = "/productos")
public class ProductoController {
private static final Logger logger = LogManager.getLogger(ProductoController.class);
    @Autowired
    private ProductoService productoService;

    @GetMapping
    public ResponseEntity<List<Producto>> listProduct(@RequestParam(name = "nombre", required = false) String nombre){

        List<Producto> productos = new ArrayList<>();
        if(null ==nombre){
            productos = productoService.listAllProductos();
            if(productos.isEmpty()){
                logger.info("La consulta de todos los productos no arrojo registros");
                return ResponseEntity.noContent().build();

            }
        }else{
            productos=productoService.findByNombre(nombre);
            if(productos.isEmpty()){
                logger.error("No se encontró un producto con el nombre especificado");
                return ResponseEntity.notFound().build();
            }
        }
        logger.info("La consulta arrojo : {}",productos);
        return ResponseEntity.ok(productos);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Producto> getProducto(@PathVariable("id") int id){
        logger.info("Obteniendo el producto con id {}",id);
        Producto producto = productoService.getProducto(id);

        if(null ==producto){
            logger.error("No se encontró un producto con el id indicado");
            return ResponseEntity.notFound().build();
        }
        logger.info("Obteniendo el producto : {}",producto);
        return ResponseEntity.ok(producto);
    }

    @PostMapping
    public ResponseEntity<Producto> createProducto(@Valid @RequestBody Producto producto, BindingResult result){
        logger.info("Creando el producto : {}",producto);
        if(result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
        }

        Producto productoCreate = productoService.createProducto(producto);
        logger.info("Producto creado");
        return ResponseEntity.status(HttpStatus.CREATED).body(productoCreate);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Producto> updateProducto(@PathVariable("id") int id,@RequestBody Producto producto){
        logger.info("Actualizando el producto {}",producto);
        producto.setIdProducto(id);
        Producto productodb = productoService.updateProducto(producto);

        if(null== productodb){
            logger.info("No se encontró un producto con el id {id}",id);
            return ResponseEntity.notFound().build();
        }
        logger.info("El producto ha sido actualizado : {}",producto);
        return ResponseEntity.ok(productodb);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Producto> deleteProducto(@PathVariable("id") int id){
        logger.info("Eliminando producto");
        Producto productoDelete = productoService.deleteProducto(id);
        if(null== productoDelete){
            logger.info("No se encontró un producto con el id {id}",id);
            return ResponseEntity.notFound().build();
        }
        logger.info("Ha sido eliminado el producto : {}",productoDelete);
        return ResponseEntity.ok(productoDelete);
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
            e.printStackTrace();
        }
        return jsonString;
    }
}
