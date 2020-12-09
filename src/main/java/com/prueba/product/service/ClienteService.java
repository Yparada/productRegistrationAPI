package com.prueba.product.service;


import com.prueba.product.entity.Cliente;

import java.util.List;

public interface ClienteService {
    public List<Cliente> listAllClientes();
    public Cliente getCliente(int id);

    public Cliente crearCliente(Cliente cliente);
    public Cliente updateCliente(Cliente cliente);
    public Cliente deleteCliente(int id);
    public Cliente findByDni(String idCliente);



}
