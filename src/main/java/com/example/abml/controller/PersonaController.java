/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.abml.controller;

import com.example.abml.model.Persona;
import com.example.abml.service.IPersonaService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ouroboros
 */
@RestController
public class PersonaController {
    @Autowired
    IPersonaService personaService;

    @GetMapping("/personas")
    public ResponseEntity getPersonas() {
        List<Persona> personas = this.personaService.getPersonas();
        return ResponseEntity.ok(personas);
    }
    
    @GetMapping("/personas/{id}")
    public ResponseEntity getPersona(@PathVariable Long id) {
        Persona persona = this.personaService.findPersona(id);
        if (persona == null) {
            Map responseBody = Map.of(
                    "error", "The requested resource does not exist",
                    "statusCode", HttpStatus.NOT_FOUND
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseBody);
        }
        return ResponseEntity.ok(persona);
    }
    
    @PostMapping("/personas")
    public ResponseEntity createPersona(@RequestBody Persona persona) {
        if (persona.getId() != null) {
            Map responseBody = Map.of(
                    "error", "The id field must be empty",
                    "statusCode", HttpStatus.BAD_REQUEST
            );
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
        }
        Persona newPersona = this.personaService.savePersona(persona);
        return ResponseEntity.ok(newPersona);
    }
    
    @DeleteMapping("/personas/{id}")
    public ResponseEntity deletePersona(@PathVariable Long id) {
        Persona existingPersona = this.personaService.findPersona(id);
        if (existingPersona == null) {
            Map responseBody = Map.of(
                    "error", "The requested ID does not exist",
                    "statusCode", HttpStatus.BAD_REQUEST
            );
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
        }
        this.personaService.deletePersona(id);
        Map response = Map.of(
                "message", "The requested element was successfully removed",
                "statusCode", HttpStatus.OK
        );
        return ResponseEntity.ok(response);
    }
    
    @PutMapping("/personas/{id}")
    public ResponseEntity editPersona(
        @PathVariable Long id,
        @RequestBody Persona persona
    ) {
        Persona existingPersona = this.personaService.findPersona(id);
        if (existingPersona == null) {
            Map responseBody = Map.of(
                    "error", "The requested ID does not exist",
                    "statusCode", HttpStatus.BAD_REQUEST
            );
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
        }
        if (existingPersona.getId() != persona.getId()) {
            Map responseBody = Map.of(
                    "error", "The IDs in the URL and the body must be equal",
                    "statusCode", HttpStatus.BAD_REQUEST
            );
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
        }
        this.personaService.savePersona(persona);
        
        return ResponseEntity.ok(persona);
    }
}
