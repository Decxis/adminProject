package com.admin.adminProject.service;

import com.admin.adminProject.repository.db1.VentaDb1Repository;
import com.admin.adminProject.repository.db2.VentaDb2Repository;
import com.admin.adminProject.util.CodigoVentaGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CodigoVentaService {

    @Autowired
    private VentaDb1Repository ventaDb1Repository;

    @Autowired
    private VentaDb2Repository ventaDb2Repository;

    public String generarCodigoUnico() {
        String codigo;
        int intentos = 0;
        do {
            if (intentos++ > 10) {
                throw new RuntimeException("No se pudo generar un código único después de 10 intentos");
            }
            codigo = CodigoVentaGenerator.generarCodigo();
        } while (codigoExisteEnAmbasBases(codigo));
        return codigo;
    }

    private boolean codigoExisteEnAmbasBases(String codigo) {
        return ventaDb1Repository.existsByCodigoVenta(codigo) || ventaDb2Repository.existsByCodigoVenta(codigo);
    }
}
