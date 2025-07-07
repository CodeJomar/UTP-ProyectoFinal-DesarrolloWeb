package com.legacy.barberia.empleado.service;

import com.legacy.barberia.auth.model.entities.Role;
import com.legacy.barberia.auth.model.entities.User;
import com.legacy.barberia.auth.model.enums.RoleList;
import com.legacy.barberia.auth.repository.RoleRepository;
import com.legacy.barberia.auth.repository.UserRepository;
import com.legacy.barberia.empleado.model.dtos.RegisEmpleadoDTO;
import com.legacy.barberia.empleado.model.entities.Empleado;
import com.legacy.barberia.empleado.model.enums.EstadoEmpleado;
import com.legacy.barberia.empleado.repository.EmpleadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmpleadoService {
    
    private final EmpleadoRepository empleadoRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    
    public Empleado registrarEmpleado(RegisEmpleadoDTO dto) {
        if (userRepository.findByUsername(dto.getUsername()).isPresent()) {
            throw new IllegalArgumentException("El usuario ya está registrado");
        }
        
        Role roleEmpleado = roleRepository.findAll().stream()
            .filter(r -> r.getName().equals(RoleList.ROLE_EMPLOYEE))
            .findFirst()
            .orElseThrow(() -> new RuntimeException("No se encontró el rol EMPLOYEE"));
        
        User usuario = new User();
        usuario.setUsername(dto.getUsername());
        usuario.setPassword(passwordEncoder.encode(dto.getPassword()));
        usuario.setRole(roleEmpleado);
        
        userRepository.save(usuario);
        
        Empleado empleado = new Empleado();
        empleado.setUsuarioEmpleado(usuario);
        empleado.setFechaContrato(dto.getFechaContrato());
        empleado.setFechaDespido(dto.getFechaDespido());
        empleado.setSalario(dto.getSalario());
        empleado.setEstado(dto.getEstado() != null ? dto.getEstado() : EstadoEmpleado.ACTIVO);
        
        return empleadoRepository.save(empleado);
    }
    
    public Empleado actualizarEmpleado(RegisEmpleadoDTO dto) {
        Empleado empleadoExistente = empleadoRepository.findById(dto.getId())
            .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));
        
        User usuarioExistente = empleadoExistente.getUsuarioEmpleado();
        
        if (!usuarioExistente.getUsername().equals(dto.getUsername())) {
            if (userRepository.findByUsername(dto.getUsername()).isPresent()) {
                throw new IllegalArgumentException("El usuario ya está registrado");
            }
            usuarioExistente.setUsername(dto.getUsername());
        }
        if (StringUtils.hasText(dto.getPassword())) {
            usuarioExistente.setPassword(passwordEncoder.encode(dto.getPassword()));
        }
        
        userRepository.save(usuarioExistente);
        
        empleadoExistente.setFechaContrato(dto.getFechaContrato());
        empleadoExistente.setFechaDespido(dto.getFechaDespido());
        empleadoExistente.setSalario(dto.getSalario());
        empleadoExistente.setEstado(dto.getEstado());
        
        return empleadoRepository.save(empleadoExistente);
    }
    
    public List<Empleado> listarEmpleados() {
        return empleadoRepository.findAll();
    }
    
    public Optional<Empleado> buscarPorId(Long id) {
        return empleadoRepository.findById(id);
    }
    
    public Optional<Empleado> buscarPorUsuarioId(String userId) {
        return empleadoRepository.findByUsuarioEmpleadoId(userId);
    }
    
    public void eliminarEmpleado(Long id) {
        empleadoRepository.deleteById(id);
    }
    
    public long contarEmpleados() {
        return empleadoRepository.count();
    }
    // Evento con Clase ENUM
    @Scheduled(cron = "0 0 0 * * *") // Todos los días a la medianoche
    public void actualizarEstadosEmpleados() {
        List<Empleado> empleados = empleadoRepository.findAll();
        LocalDate hoy = LocalDate.now();
        
        for (Empleado empleado : empleados) {
            EstadoEmpleado estadoActual = empleado.getEstado();
            EstadoEmpleado nuevoEstado = estadoActual;
            
            // Verifica si ya terminó el contrato
            if (empleado.getFechaDespido() != null && empleado.getFechaDespido().isBefore(hoy)) {
                nuevoEstado = EstadoEmpleado.INACTIVO;
            } else {
                // Si hoy está entre fechaContrato y fechaDespido (o no hay despido), marcar como ACTIVO
                if (empleado.getFechaContrato() != null &&
                    (empleado.getFechaDespido() == null || !empleado.getFechaDespido().isBefore(hoy))) {
                    nuevoEstado = EstadoEmpleado.ACTIVO;
                }
            }
            
            // Guardar solo si hay cambio
            if (!estadoActual.equals(nuevoEstado)) {
                empleado.setEstado(nuevoEstado);
                empleadoRepository.save(empleado);
            }
        }
    }
    
    
}
