package com.legacy.barberia.cita.service;

import com.legacy.barberia.auth.model.entities.User;
import com.legacy.barberia.cita.model.dto.CitaDTO;
import com.legacy.barberia.cita.model.entities.Cita;
import com.legacy.barberia.cita.repository.CitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CitaService {

    @Autowired
    private CitaRepository citaRepository;

    public List<Cita> listarPorUsuario(User user){
        return citaRepository.findByUser(user);
    }

    public List <Cita> listarCitas(){
        return citaRepository.findAll();
    }
    
    public List <Cita> obtenerCitasGeneradas(){
        return citaRepository.findAll();
    }

    public Cita guardarCita(Cita cita){
        return citaRepository.save(cita);
    }

    public void eliminarCita(Long id){
        citaRepository.deleteById(id);
    }
    
    public Optional<Cita> buscarPorId(Long id) {
        return citaRepository.findById(id);
    }
    
    public Optional<CitaDTO> obtenerDTOporID(Long id){
        return citaRepository.findById(id).map(this::obtenerDTO);
    }

    public CitaDTO obtenerDTO (Cita cita){
        CitaDTO dto = new CitaDTO();
        dto.setId(cita.getId());
        dto.setFecha(cita.getFecha());
        dto.setHora(cita.getHora());
        dto.setObservaciones(cita.getObservaciones());
        dto.setMotivo(cita.getMotivo().name());
        dto.setEstado(cita.getEstado().name());

        if (cita.getUser() !=null){
            dto.setUserEmail(cita.getUser().getUsername());
        }
        return dto;
    }
}
