package com.prueba.product.service;

import com.prueba.product.entity.Producto;

import java.util.List;

public interface ProductoService {
    public List<Producto> listAllProductos();
    public Producto getProducto(int id);

    public Producto createProducto(Producto producto);
    public Producto updateProducto(Producto producto);
    public Producto deleteProducto(int id);
    public List<Producto> findByNombre(String nombre);
}
