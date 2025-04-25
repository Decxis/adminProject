package com.admin.adminProject.controllers;

import com.admin.adminProject.dto.VentaDTO;
import com.admin.adminProject.model.db1.VentaDb1;
import com.admin.adminProject.model.db2.VentaDb2;
import com.admin.adminProject.service.CodigoVentaService;
import com.admin.adminProject.service.VentaDb1Service;
import com.admin.adminProject.service.VentaDb2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/ventas")
@CrossOrigin
public class VentaController {

    private final VentaDb1Service ventaDb1Service;
    private final VentaDb2Service ventaDb2Service;

    public VentaController(VentaDb1Service ventaDb1Service, VentaDb2Service ventaDb2Service) {
        this.ventaDb1Service = ventaDb1Service;
        this.ventaDb2Service = ventaDb2Service;
    }
    @Autowired
    private CodigoVentaService codigoVentaService;

    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody VentaDTO dto, @RequestParam String bd) {
        if ("sqlserver".equalsIgnoreCase(bd)) {
            // Generar código único
            String codigoUnico = codigoVentaService.generarCodigoUnico();
            VentaDb1 v = ventaDb1Service.guardar(dto, codigoUnico);
            return ResponseEntity.ok(v);
        } else if ("postgres".equalsIgnoreCase(bd)) {
            // Generar código único
            String codigoUnico = codigoVentaService.generarCodigoUnico();
            VentaDb2 v = ventaDb2Service.guardar(dto,codigoUnico);
            return ResponseEntity.ok(v);
        } else {
            return ResponseEntity.badRequest().body("Base de datos no válida");
        }
    }
    @PostMapping("all")
    public ResponseEntity<?> guardarAll(@RequestBody VentaDTO dto) {
        String codigoUnico = codigoVentaService.generarCodigoUnico();
        VentaDb1 v1 = ventaDb1Service.guardar(dto,codigoUnico);
        VentaDb2 v2 = ventaDb2Service.guardar(dto,codigoUnico);
        return ResponseEntity.ok("Guardado en ambas bases de datos");
    }
}
