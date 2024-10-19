package com.devhoss.inventory.controller;


import com.devhoss.inventory.models.Category;
import com.devhoss.inventory.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryRestController {

    @Autowired
    private ICategoryService iCategoryService;

    @GetMapping
    public List<Category> findAll(){
        return iCategoryService.findAll();
    }




    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Category save(@RequestBody Category category){
        return iCategoryService.save(category);
    }
/*
    @GetMapping("/{id}")
    public ResponseEntity<Empleado> getCategoryById(@PathVariable("id") UUID id){
        System.out.println(id);
        return iempleadoService.getEmpleadoById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PutMapping("/{id}")
    public ResponseEntity<Empleado> actualizarEmpleado(@PathVariable("id") UUID id, @RequestBody Empleado empleado){
        return iempleadoService.getEmpleadoById(id)
                .map(empleadoGuardado -> {
            empleadoGuardado.setNombre(empleado.getNombre());
            empleadoGuardado.setApellido(empleado.getApellido());
            empleadoGuardado.setEmail(empleado.getEmail());
            empleadoGuardado.setDni(empleado.getDni());

            Empleado empleadoActualizado = iempleadoService.updateEmpleado(empleadoGuardado);
            return new ResponseEntity<>(empleadoActualizado,HttpStatus.OK);
        })
        .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarEmpleado(@PathVariable("id") UUID id){
        iempleadoService.deleteEmpleado(id);
        return new ResponseEntity<String>("Empleado eliminado exitosamente",HttpStatus.OK);
    }
    */
}