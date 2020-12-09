package com.prueba.product.service;

import com.prueba.product.entity.Cliente;
import com.prueba.product.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    public final ClienteRepository clienteRepository;

    @Override
    public List<Cliente> listAllClientes() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente crearCliente(Cliente cliente) {
        Cliente  clientedb = clienteRepository.findByDni(cliente.getDni());
        if (clientedb!=null){
            return clientedb;
        }
        clientedb = clienteRepository.save(cliente);
        return clientedb;
    }

    @Override
    public Cliente updateCliente(Cliente cliente) {
        Cliente clientedb = getCliente(cliente.getIdCliente());
        if(null==clientedb){
            return null;
        }
        clientedb.setNombre(cliente.getNombre());
        clientedb.setApellido(cliente.getApellido());
        clientedb.setDni(cliente.getDni());
        clientedb.setTelefono(cliente.getTelefono());
        clientedb.setEmail(cliente.getEmail());
        return null;
    }

    @Override
    public Cliente deleteCliente(int id) {
        Cliente clientedb = getCliente(id);
        if(null==clientedb){
            return null;
        }
        clienteRepository.delete(clientedb);
        return clientedb;
    }

    @Override
    public Cliente findByDni(String dni) {
        return clienteRepository.findByDni(dni);
    }


    @Override
    public Cliente getCliente(int id) {
        return clienteRepository.findById(id).orElse(null);
    }
}
