package com.legacy.barberia.empleado.repository;

import com.legacy.barberia.empleado.model.entities.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {
    Optional<Empleado> findByUsuarioEmpleadoId(String usuarioEmpleado);
    Optional<Empleado> findByUsuarioEmpleadoUsername(String username);
}
