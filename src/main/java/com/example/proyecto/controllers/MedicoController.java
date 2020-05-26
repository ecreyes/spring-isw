package com.example.proyecto.controllers;

import com.example.proyecto.models.Medico;
import com.example.proyecto.services.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/medicos")
public class MedicoController {

    @Autowired
    @Qualifier("MedicoService")
    private MedicoService medicoService;

    @GetMapping("")
    public Iterable<Medico> getMedicos(){
        return medicoService.listAll();
    }

    @GetMapping("/search")
    public Iterable<Medico> getMedicosByState(@RequestParam(value="estado") String estado){
        return medicoService.findMedicoByState(estado);
    }

    @PostMapping("")
    public ResponseEntity<Medico> addMedico(@RequestBody Medico medico){
        Medico med = medicoService.saveOrUpdateMedico(medico);
        return new ResponseEntity<Medico>(med, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity getMedico(@PathVariable Long id){
        Medico med = medicoService.findMedicById(id);
        if(med!=null) {
            return new ResponseEntity<Medico>(med, HttpStatus.OK);
        }
        return new ResponseEntity<>(false,HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateMedico(@PathVariable Long id,@RequestBody Medico newMedico){
        Medico med = medicoService.findMedicById(id);
        if(med!=null){
            med.setCargo(newMedico.getCargo());
            med.setEstado(newMedico.getEstado());
            med.setNombre(newMedico.getNombre());
            medicoService.saveOrUpdateMedico(med);
            return new ResponseEntity<Medico>(med, HttpStatus.OK);
        }
        return new ResponseEntity<>(false,HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteMedico(@PathVariable Long id) {
        Medico medico = medicoService.findMedicById(id);
        if(medico!=null){
            medicoService.delete(id);
            return new ResponseEntity<>(true,HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(false,HttpStatus.BAD_REQUEST);
    }

}
