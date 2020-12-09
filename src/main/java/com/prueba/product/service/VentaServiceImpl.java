package com.prueba.product.service;

import com.prueba.product.entity.Cliente;
import com.prueba.product.entity.Venta;
import com.prueba.product.repository.VentaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VentaServiceImpl implements VentaService{

    @Autowired
    private final VentaRepository ventaRepository;

    @Override
    public List<Venta> listAllVenta() {
        return ventaRepository.findAll();
    }

    @Override
    public Venta getVenta(int id) {
        return ventaRepository.findById(id).orElse(null);
    }

    @Override
    public Venta createVenta(Venta venta) {
        venta.setFecha(new Date());
        return ventaRepository.save(venta);
    }

    @Override
    public Venta updateVenta(Venta venta) {
        Venta ventadb = getVenta(venta.getIdVenta());
        if (null==ventadb){
            return null;
        }
        ventadb.setFecha(new Date());
        return ventaRepository.save(ventadb);
    }

    @Override
    public Venta deleteVenta(int id) {
        Venta ventadb = getVenta(id);
        if (null==ventadb){
            return null;
        }
        ventaRepository.delete(ventadb);
        return ventadb;
    }


    @Override
    public List<Venta> findByIdCliente(Cliente idCliente) {
        return ventaRepository.findByIdCliente(idCliente);
    }
}
