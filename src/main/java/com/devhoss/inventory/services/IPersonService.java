package com.devhoss.inventory.services;


import com.devhoss.inventory.model.PersonEntity;
import com.devhoss.inventory.response.PersonResponseRest;
import org.springframework.http.ResponseEntity;

public interface IPersonService {

    public ResponseEntity<PersonResponseRest> search();

    public ResponseEntity<PersonResponseRest> save(PersonEntity person);

    public ResponseEntity<PersonResponseRest> deleteById(Long id);
}
