package com.prueba.product;

import com.prueba.product.entity.Cliente;
import com.prueba.product.entity.DetalleVenta;
import com.prueba.product.entity.Venta;
import com.prueba.product.repository.VentaRepository;
import com.prueba.product.service.VentaService;
import com.prueba.product.service.VentaServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.Optional;

@SpringBootTest
public class VentaServiceMockTest {

    @Mock
    private VentaRepository ventaRepository;

    private VentaService ventaService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this);
        ventaService = new VentaServiceImpl(ventaRepository);
        Venta gomitas = Venta.builder()
                .idVenta(Integer.parseInt("1"))
                .idCliente(Cliente.builder().idCliente(Integer.parseInt("1")).build())
                .fecha(new Date())
                .idDetalleVenta(DetalleVenta.builder().idDetalleVenta(Integer.parseInt("1")).build())
                .build();

        Mockito.when(ventaRepository.findById(Integer.parseInt("1")))
                .thenReturn(Optional.of(gomitas));
    }

    @Test
    public void whenValidGetID_ThenReturnIdCliente(){
        Venta found = ventaService.getVenta(1);
        Assertions.assertThat(found.getIdCliente().getIdCliente()).isEqualTo(Integer.parseInt("1"));
    }
}
