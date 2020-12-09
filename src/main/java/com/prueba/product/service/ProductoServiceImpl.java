package com.prueba.product.service;

import com.prueba.product.entity.Producto;
import com.prueba.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductoServiceImpl implements ProductoService{

    @Autowired
    private final ProductRepository productRepository;

    @Override
    public List<Producto> listAllProductos() {
        return productRepository.findAll();
    }

    @Override
    public Producto getProducto(int id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public Producto createProducto(Producto producto) {
        return productRepository.save(producto);
    }

    @Override
    public Producto updateProducto(Producto producto) {
        Producto productodb = getProducto(producto.getIdProducto());
        if(null==productodb){
            return null;
        }
        productodb.setNombre(producto.getNombre());
        productodb.setPrecio(producto.getPrecio());

        return productRepository.save(productodb);
    }


    @Override
    public Producto deleteProducto(int id) {
        Producto productodb = getProducto(id);
        if(null==productodb){
            return null;
        }
        productRepository.delete(productodb);
        return productodb;
    }

    @Override
    public List<Producto> findByNombre(String nombre) {
        return productRepository.findByNombre(nombre);
    }
}
