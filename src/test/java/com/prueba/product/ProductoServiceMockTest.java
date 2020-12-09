package com.prueba.product;

import com.prueba.product.entity.Producto;
import com.prueba.product.repository.ProductRepository;
import com.prueba.product.service.ProductoService;
import com.prueba.product.service.ProductoServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class ProductoServiceMockTest {


    @Mock
    public ProductRepository productRepository;

    public ProductoService productoService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this);
        productoService = new ProductoServiceImpl(productRepository);
        Producto yogurt = Producto.builder()
                .nombre("yogurt")
                .precio(2000)
                .build();
        Mockito.when(productRepository.findById(1))
                .thenReturn(Optional.of(yogurt));
        Mockito.when(productRepository.save(yogurt))
                .thenReturn(yogurt);

    }

    @Test
    public void whenValidGetID_ThenReturnProduct(){
        Producto found = productoService.getProducto(1);
        Assertions.assertThat(found.getNombre()).isEqualTo("yogurt");
    }

    @Test
   public void whenUpdateNombre_ThenReturnProduct(){
        Producto created = productoService.updateProducto(Producto.builder().idProducto(1).nombre("yogurt griego").precio(2200).build());
        Assertions.assertThat(created.getNombre()).isEqualTo("yogurt griego");

   }

}