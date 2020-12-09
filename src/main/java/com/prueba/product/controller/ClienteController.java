package com.prueba.product.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prueba.product.components.ErrorMessage;
import com.prueba.product.entity.Cliente;
import com.prueba.product.service.ClienteService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {
    private static final Logger logger = LogManager.getLogger(ClienteController.class);

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<Cliente> createCliente (@Valid @RequestBody Cliente cliente, BindingResult result){
        logger.info("Creando el producto : {}",cliente);
        //log.info("Creando el producto : {}",cliente);
        if (result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
        }

        Cliente clienteCreate = clienteService.crearCliente(cliente);
        logger.info("Cliente creado");
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteCreate);
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
            logger.error("Se ha registrado un error ", e.getMessage());
            //logger.atError().withThrowable(e).log("Se ha registrado un error ");
            e.printStackTrace();
        }
        return jsonString;
    }
}
