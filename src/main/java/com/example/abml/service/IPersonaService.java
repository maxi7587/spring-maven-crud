/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.abml.service;

import com.example.abml.model.Persona;
import java.util.List;

/**
 *
 * @author ouroboros
 */
public interface IPersonaService {
    public List<Persona> getPersonas();
    public Persona savePersona(Persona persona);
    public void deletePersona(Long id);
    public Persona findPersona(Long id);
}
