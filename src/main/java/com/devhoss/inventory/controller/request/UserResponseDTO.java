package com.devhoss.inventory.controller.request;


import com.devhoss.inventory.model.RoleEntity;
import lombok.Data;

import java.util.Set;

@Data
public class UserResponseDTO {

    private Long id;
    private String email;
    private String username;
    private Set<RoleEntity> roles;


}


