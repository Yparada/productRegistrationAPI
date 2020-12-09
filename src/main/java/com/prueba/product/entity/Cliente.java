package com.prueba.product.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "Cliente")
@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private int idCliente;

    @NotEmpty(message = "El campo nombre no debe estar vacio")
    private String nombre;

    @NotEmpty(message = "El campo apellido no debe estar vacio")
    private String apellido;

    @NotEmpty(message = "El campo apellido no debe estar vacio")
    @Column(unique = true, nullable = false)
    private String dni;

    @NotEmpty(message = "El campo apellido no debe estar vacio")
    private String telefono;

    @NotEmpty(message = "El campo email no debe estar vacio")
    @Email(message = "El campo correo es  invalido")
    private String email;
}
