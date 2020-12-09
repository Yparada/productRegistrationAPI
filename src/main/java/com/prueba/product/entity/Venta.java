package com.prueba.product.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor @NoArgsConstructor @Builder
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_venta")
    private int idVenta;

    @NotNull(message = "El campo idcliente no debe estar vacio")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Cliente idCliente;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;

    @NotNull(message = "El campo idDetalleVenta no debe estar vacio")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_detalle_venta")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private DetalleVenta idDetalleVenta;

}
