package com.devhoss.inventory.controller;



import com.devhoss.inventory.controller.request.CreateUserDTO;
import com.devhoss.inventory.controller.request.UserResponseDTO;
import com.devhoss.inventory.mapper.UserMapper;
import com.devhoss.inventory.model.ERole;
import com.devhoss.inventory.model.RoleEntity;
import com.devhoss.inventory.model.UserEntity;
import com.devhoss.inventory.repository.IUserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class UsersController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private IUserRepository userRepository;


    @GetMapping("/users")
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        List<UserEntity> userEntities = (List<UserEntity>) userRepository.findAll();

        List<UserResponseDTO> userDTOs = userEntities.stream()
                .map(userEntity -> new UserMapper().toDTO(userEntity))
                .collect(Collectors.toList());

        return ResponseEntity.ok(userDTOs);
    }


    @PostMapping("/createUser")
    public ResponseEntity<?> createUser(@Valid @RequestBody CreateUserDTO createUserDTO) {

        Set<RoleEntity> roles = createUserDTO.getRoles().stream()
                .map(role -> RoleEntity.builder()
                        .name(ERole.valueOf(role))
                        .build())
                .collect(Collectors.toSet());

        UserEntity userEntity = new UserEntity().builder()
                .username(createUserDTO.getUsername())
                .password(passwordEncoder.encode(createUserDTO.getPassword()))
                .email(createUserDTO.getEmail())
                .roles(roles)
                .build();

        userRepository.save(userEntity);

        return ResponseEntity.ok(userEntity);

    }

    @DeleteMapping("/deleteUser")
    public String deleteUser(@RequestParam String id) {

            userRepository.deleteById(Long.parseLong(id));

            return "User " + id + " deleted";
    }
}
