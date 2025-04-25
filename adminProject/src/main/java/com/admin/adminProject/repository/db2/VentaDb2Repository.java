package com.admin.adminProject.repository.db2;

import com.admin.adminProject.model.db2.VentaDb2;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VentaDb2Repository extends JpaRepository<VentaDb2, Long> {
    boolean existsByCodigoVenta(String codigoVenta);

}
