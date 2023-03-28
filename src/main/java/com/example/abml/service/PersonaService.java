/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.abml.service;

import com.example.abml.model.Persona;
import com.example.abml.repository.PersonaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ouroboros
 */
@Service
public class PersonaService implements IPersonaService {
    @Autowired
    private PersonaRepository personaRepository;

    @Override
    public List<Persona> getPersonas() {
        List<Persona> listaPersonas = this.personaRepository.findAll();
        return listaPersonas;
    }

    /**
     *
     * @param persona
     * @return
     */
    @Override
    public Persona savePersona(Persona persona) {
        return this.personaRepository.save(persona);
    }

    @Override
    public void deletePersona(Long id) {
        this.personaRepository.deleteById(id);
    }

    @Override
    public Persona findPersona(Long id) {
        Persona persona = this.personaRepository.findById(id).orElse(null);
        return persona;
    }
}
