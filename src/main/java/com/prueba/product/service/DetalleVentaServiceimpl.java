package com.prueba.product.service;

import com.prueba.product.entity.DetalleVenta;
import com.prueba.product.repository.DetalleVentaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DetalleVentaServiceimpl implements DetalleVentaService{

    @Autowired
    private final DetalleVentaRepository detalleVentaRepository;

    @Override
    public DetalleVenta findByIdVenta(int idVenta) {

        return detalleVentaRepository.findByIdVenta(idVenta);
    }
}
