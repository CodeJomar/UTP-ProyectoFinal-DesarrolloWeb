package com.legacy.barberia.cita.repository;

import com.legacy.barberia.auth.model.entities.User;
import com.legacy.barberia.cita.model.entities.Cita;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CitaRepository extends JpaRepository<Cita, Long> {
    List<Cita> findByUser(User user);
}
