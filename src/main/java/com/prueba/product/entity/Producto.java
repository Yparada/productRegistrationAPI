package com.prueba.product.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "Producto")
@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private int idProducto;

    @NotEmpty(message = "El campo nombre no debe estar vacio")
    private String nombre;
    private int precio;

}
