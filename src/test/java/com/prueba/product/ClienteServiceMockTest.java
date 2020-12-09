package com.prueba.product;

import com.prueba.product.entity.Cliente;
import com.prueba.product.repository.ClienteRepository;
import com.prueba.product.service.ClienteService;
import com.prueba.product.service.ClienteServiceImpl;
import com.prueba.product.service.ProductoService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class ClienteServiceMockTest {

    @Mock
    public ClienteRepository clienteRepository;

    public ClienteService clienteService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this);
        clienteService = new ClienteServiceImpl(clienteRepository);
        Cliente cliente1 = Cliente.builder()
                .nombre("Carlos")
                .apellido("Vargas")
                .dni("256")
                .telefono("77777")
                .email("CVargas@hub.ca")
                .build();
        Mockito.when(clienteRepository.findById(1))
                .thenReturn(Optional.of(cliente1));
        Mockito.when(clienteRepository.save(cliente1))
                .thenReturn(cliente1);
    }

    @Test
    public void whenValidGetID_ThenReturnCliente(){
        Cliente found = clienteService.crearCliente(Cliente.builder()
                .idCliente(1)
                .nombre("Juan")
                .apellido("Ortega")
                .dni("888")
                .telefono("99999")
                .email("JOrtega@local.es")
                .build());
        Assertions.assertThat(found.getNombre()).isEqualTo("Juan");
    }

    @Test
    public void whenValidGetID_ThenReturnClientee() {
        Cliente found2 = clienteService.getCliente(1);
        Assertions.assertThat(found2.getNombre()).isEqualTo("Carlos");

    }

}
