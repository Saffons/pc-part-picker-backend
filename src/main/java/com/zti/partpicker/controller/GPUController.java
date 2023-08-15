package com.zti.partpicker.controller;

import java.util.List;

import com.zti.partpicker.exception.GPUNotFoundException;
import com.zti.partpicker.model.GPU;
import com.zti.partpicker.repository.ConfigurationRepository;
import com.zti.partpicker.repository.GPURepository;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/parts/gpu")
public class GPUController {

    @Autowired
    private final GPURepository repository;

    @Autowired
    private final ConfigurationRepository configurationRepository;

    GPUController(GPURepository repository, ConfigurationRepository configurationRepository) {
        this.repository = repository;
        this.configurationRepository = configurationRepository;
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
    ResponseEntity<String> deleteGPU(@PathVariable Long id) {
        if (configurationRepository.findAllByGpu(id).size() > 0) {
            return ResponseEntity.badRequest().build();
        };

        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}