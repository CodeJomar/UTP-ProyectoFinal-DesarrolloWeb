package com.legacy.barberia.cita.service;

import com.legacy.barberia.cita.model.Cita;
import com.legacy.barberia.cita.repository.CitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CitaService {

    @Autowired
    private CitaRepository citaRepository;

    public List<Cita> listarPorUsuario(Long usuarioId){
      return citaRepository.findByUsuarioId(usuarioId);
    }
    
    public Cita guardarCita(Cita cita){
      return citaRepository.save(cita);
    }
    
    public void eliminarCita(Long id){
      citaRepository.deleteById(id);
    }
}
