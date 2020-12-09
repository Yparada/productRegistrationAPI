package com.prueba.product.repository;

import com.prueba.product.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    public Cliente findByDni(String idCliente);
}
