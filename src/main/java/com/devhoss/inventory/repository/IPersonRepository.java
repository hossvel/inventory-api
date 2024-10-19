package com.devhoss.inventory.repository;


import com.devhoss.inventory.model.PersonEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPersonRepository extends CrudRepository<PersonEntity, Long> {
}
