package com.admin.adminProject.model.db1;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.print.CancelablePrintJob;
import java.time.LocalDate;

@Entity
@Table(name = "ventas")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VentaDb1 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fecha;
    private Double monto;
    private String producto;
    private String cliente;
    @Column(name = "usuario_id")
    private Integer usuarioId;
    @Column(name = "base_datos")
    private Integer databaseId;
    @Column(name = "venta_codigo", unique = true, nullable = false)
    private String codigoVenta;

}

