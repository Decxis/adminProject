package com.admin.adminProject.repository.db1;

import com.admin.adminProject.model.db1.VentaDb1;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VentaDb1Repository extends JpaRepository<VentaDb1, Long> {
    boolean existsByCodigoVenta(String codigoVenta);

}
