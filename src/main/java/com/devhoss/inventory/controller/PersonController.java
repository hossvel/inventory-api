package com.devhoss.inventory.controller;


import com.devhoss.inventory.model.PersonEntity;
import com.devhoss.inventory.response.PersonResponseRest;
import com.devhoss.inventory.services.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PersonController {

    @Autowired
    private IPersonService personService;


    @GetMapping("/hello")
    public String hello(){
        return "Bienvenido a mi API REST con Spring Security JWT";
    }

    @GetMapping("/persons")
    public ResponseEntity<PersonResponseRest> search(){

        ResponseEntity<PersonResponseRest> response = personService.search();
        return response;
    }

    @PostMapping("/savePerson")
    public ResponseEntity<PersonResponseRest> save(@RequestBody PersonEntity person){

        ResponseEntity<PersonResponseRest> response = personService.save(person);
        return response;
    }

    @DeleteMapping("/deletePerson")
    public ResponseEntity<PersonResponseRest> deleteById(@RequestParam Long id){

        ResponseEntity<PersonResponseRest> response = personService.deleteById(id);
        return response;
    }
}
