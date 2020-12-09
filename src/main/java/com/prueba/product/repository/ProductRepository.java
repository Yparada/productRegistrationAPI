package com.prueba.product.repository;

import com.prueba.product.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface ProductRepository extends JpaRepository<Producto, Integer> {
    public List<Producto> findByNombre(String nombre);
}
