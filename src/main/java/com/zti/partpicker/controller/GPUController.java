package com.zti.partpicker.controller;

import java.util.List;

import com.zti.partpicker.exception.GPUNotFoundException;
import com.zti.partpicker.model.GPU;
import com.zti.partpicker.repository.GPURepository;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/parts/gpu")
public class GPUController {

    @Autowired
    private final GPURepository repository;

    GPUController(GPURepository repository) {
        this.repository = repository;
    }

    @RolesAllowed("ROLE_ADMIN")
    @GetMapping
    List<GPU> all() {
        return repository.findAll();
    }

    @PostMapping
    GPU newGPU(@RequestBody GPU newGPU) {
        return repository.save(newGPU);
    }

    @RolesAllowed("ROLE_USER")
    @GetMapping("/{id}")
    GPU one(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new GPUNotFoundException(id));
    }

    @DeleteMapping("/{id}")
    void deleteGPU(@PathVariable Long id) {
        repository.deleteById(id);
    }
}