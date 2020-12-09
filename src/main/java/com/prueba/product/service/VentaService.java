package com.prueba.product.service;

import com.prueba.product.entity.Cliente;
import com.prueba.product.entity.Venta;

import java.util.List;

public interface VentaService {
    public List<Venta> listAllVenta();
    public Venta getVenta(int id);

    public Venta createVenta(Venta venta);
    public Venta updateVenta(Venta venta);
    public Venta deleteVenta(int id);
    public List<Venta> findByIdCliente(Cliente idCliente);

}
