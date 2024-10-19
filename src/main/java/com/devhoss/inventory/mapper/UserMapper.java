package com.devhoss.inventory.mapper;


import com.devhoss.inventory.controller.request.UserResponseDTO;
import com.devhoss.inventory.model.UserEntity;


public class UserMapper {

    public UserResponseDTO toDTO(UserEntity userEntity) {
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(userEntity.getId());
        dto.setEmail(userEntity.getEmail());
        dto.setUsername(userEntity.getUsername());
        dto.setRoles(userEntity.getRoles());
        return dto;
    }
}
