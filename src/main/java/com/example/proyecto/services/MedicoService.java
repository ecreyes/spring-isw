package com.example.proyecto.services;

import com.example.proyecto.daos.MedicoRepository;
import com.example.proyecto.models.Medico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("MedicoService")
public class MedicoService {
    @Autowired
    private MedicoRepository medicoRepository;

    public Medico saveOrUpdateMedico(Medico medico){
        return medicoRepository.save(medico);
    }

    public Medico findMedicById(Long id){
        return medicoRepository.findById(id).orElse(null);
    }

    public Iterable<Medico> listAll(){
        return medicoRepository.findAll();
    }

    public Iterable<Medico> findMedicoByState(String estado){
        return medicoRepository.findByEstado(estado);
    }

    public void delete(Long id){
        medicoRepository.deleteById(id);
    }
}
