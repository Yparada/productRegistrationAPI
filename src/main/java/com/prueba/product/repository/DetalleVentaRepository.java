package com.prueba.product.repository;

import com.prueba.product.entity.DetalleVenta;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DetalleVentaRepository extends JpaRepository<DetalleVenta,Integer> {
    public DetalleVenta findByIdVenta(int idVenta);
}
