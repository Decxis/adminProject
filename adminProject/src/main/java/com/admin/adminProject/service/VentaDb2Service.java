package com.admin.adminProject.service;

import com.admin.adminProject.dto.VentaDTO;
import com.admin.adminProject.model.db2.VentaDb2;
import com.admin.adminProject.repository.db2.VentaDb2Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VentaDb2Service {

    private final VentaDb2Repository repo;

    public VentaDb2Service(VentaDb2Repository repo) {
        this.repo = repo;
    }
    public VentaDb2 guardar(VentaDTO dto, String codigo) {

        VentaDb2 venta = new VentaDb2();
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