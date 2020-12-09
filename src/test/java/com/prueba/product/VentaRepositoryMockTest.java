package com.prueba.product;

import com.prueba.product.entity.Cliente;
import com.prueba.product.entity.DetalleVenta;
import com.prueba.product.entity.Venta;
import com.prueba.product.repository.VentaRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;
import java.util.List;

@DataJpaTest
public class VentaRepositoryMockTest {

    @Autowired
    private VentaRepository ventaRepository;

    @Test
    public void whenFindByCliente_thenReturnListVenta(){
        Venta venta01 = Venta.builder()

                .fecha(new Date())
                .idCliente(Cliente.builder().idCliente(Integer.parseInt("1")).build())
                .idDetalleVenta(DetalleVenta.builder().idDetalleVenta(Integer.parseInt("1")).build())
                .build();
        ventaRepository.save(venta01);

        //List<Venta> founds = ventaRepository.findByIdCliente(venta01.getIdCliente());
        List<Venta> founds = ventaRepository.findByIdCliente(venta01.getIdCliente());
        Assertions.assertThat(founds.size()).isEqualTo(3);



    }
}
