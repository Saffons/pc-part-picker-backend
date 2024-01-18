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

/**
 * Controller class responsible for handling GPU-related operations and endpoints.
 */
@CrossOrigin("*")
@RestController
@RequestMapping("/api/parts/gpu")
public class GPUController {

    @Autowired
    private final GPURepository repository;

    @Autowired
    private final ConfigurationRepository configurationRepository;

    /**
     * Constructor for the GPUController class.
     *
     * @param repository An instance of GPURepository for interacting with the GPUs' data.
     * @param configurationRepository An instance of ConfigurationRepository for preparing JSON responses
     */
    GPUController(GPURepository repository, ConfigurationRepository configurationRepository) {
        this.repository = repository;
        this.configurationRepository = configurationRepository;
    }

    /**
     * Retrieves a list of all GPUs.
     *
     * @return A list of GPU objects.
     */
    @RolesAllowed("ROLE_ADMIN")
    @GetMapping
    List<GPU> all() {
        return repository.findAll();
    }

    /**
     * Creates a new GPU.
     *
     * @param newGPU The GPU object containing the details of the new GPU.
     * @return The saved GPU object.
     */
    @PostMapping
    GPU newGPU(@RequestBody GPU newGPU) {
        return repository.save(newGPU);
    }

    /**
     * Retrieves details of a single GPU by its ID.
     *
     * @param id The ID of the GPU to retrieve.
     * @return The GPU object corresponding to the provided ID.
     * @throws GPUNotFoundException If no GPU with the given ID is found.
     */
    @RolesAllowed("ROLE_USER")
    @GetMapping("/{id}")
    GPU one(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new GPUNotFoundException(id));
    }

    /**
     * Deletes a GPU by its ID.
     *
     * @param id The ID of the GPU to delete.
     */
    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteGPU(@PathVariable Long id) {
        if (configurationRepository.findAllByGpu(id).size() > 0) {
            return ResponseEntity.badRequest().build();
        };

        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}