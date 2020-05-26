package com.example.proyecto.daos;

import com.example.proyecto.models.Medico;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface MedicoRepository extends CrudRepository<Medico,Long> {

    Iterable<Medico> findByEstado(String estado);
}
