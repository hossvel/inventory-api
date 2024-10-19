package com.devhoss.inventory.repository;


import com.devhoss.inventory.model.RoleEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoleRepository extends CrudRepository<RoleEntity, Long> {
}
