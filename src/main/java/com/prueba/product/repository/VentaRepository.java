package com.prueba.product.repository;

import com.prueba.product.entity.Cliente;
import com.prueba.product.entity.Venta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VentaRepository extends JpaRepository<Venta,Integer> {
    public List<Venta> findByIdCliente(Cliente idCliente);

}
