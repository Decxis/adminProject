package com.admin.adminProject.service;

import com.admin.adminProject.dto.VentaDTO;
import com.admin.adminProject.model.db1.VentaDb1;
import com.admin.adminProject.repository.db1.VentaDb1Repository;
import org.springframework.stereotype.Service;

@Service
public class VentaDb1Service {

    private final VentaDb1Repository repo;

    public VentaDb1Service(VentaDb1Repository repo) {
        this.repo = repo;
    }

    public VentaDb1 guardar(VentaDTO dto, String codigo) {
        VentaDb1 venta = new VentaDb1();
        venta.setCliente(dto.getCliente());
        venta.setProducto(dto.getProducto());
        venta.setMonto(dto.getMonto());
        venta.setFecha(dto.getFecha());
        venta.setUsuarioId(dto.getUsuarioId());
        venta.setDatabaseId(dto.getDatabaseId());
        venta.setCodigoVenta(codigo);
        return repo.save(venta);
    }
}