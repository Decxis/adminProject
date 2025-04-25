package com.admin.adminProject.dto;

import lombok.Data;

import java.time.LocalDate;
@Data
public class VentaDTO {
    private Long id;
    private LocalDate fecha;
    private Double monto;
    private String producto;
    private String cliente;
    private Integer usuarioId;
    private Integer databaseId;
}
