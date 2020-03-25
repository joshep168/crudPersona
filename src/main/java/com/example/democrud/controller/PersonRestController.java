package com.example.democrud.controller;
import com.example.democrud.model.Persona;
import com.example.democrud.service.api.PersonaServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value="/api/v1/")
public class PersonRestController {

    @Autowired
    private PersonaServiceAPI personaServiceAPI;

    @GetMapping(value="/all")
    public List<Persona> getAll(){
        return personaServiceAPI.getAll();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value = "/get/{id}")
    public Persona get(@PathVariable Long id){
        return personaServiceAPI.get(id);
    }

    @PostMapping(value = "/save")
    public ResponseEntity<Persona> save(@RequestBody Persona persona){
       Persona obj =  personaServiceAPI.save(persona);
       return new ResponseEntity<Persona>(obj, HttpStatus.OK);
    }

    @GetMapping(value = "/delete/{id}")
    public ResponseEntity<Persona> delete(@PathVariable Long id){
        Persona persona = personaServiceAPI.get(id);
        if(persona!=null){
            personaServiceAPI.delete(id);
            return new ResponseEntity<Persona>(persona,HttpStatus.OK);
        }else{
            return new ResponseEntity<Persona>(persona,HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}

