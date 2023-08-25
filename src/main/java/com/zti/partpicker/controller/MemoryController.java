package com.zti.partpicker.controller;

import java.util.List;

import com.zti.partpicker.exception.MemoryNotFoundException;
import com.zti.partpicker.model.Memory;
import com.zti.partpicker.repository.ConfigurationRepository;
import com.zti.partpicker.repository.MemoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller class responsible for handling Memory-related operations and endpoints.
 */
@CrossOrigin(origins = "https://kaktus-react-spring.netlify.app")
@RestController
@RequestMapping("/api/parts/memory")
public class MemoryController {

    @Autowired
    private final MemoryRepository repository;

    @Autowired
    private final ConfigurationRepository configurationRepository;

    /**
     * Constructor for the MemoryController class.
     *
     * @param repository An instance of MemoryRepository for interacting with the Memory's data.
     * @param configurationRepository An instance of ConfigurationRepository for preparing JSON responses
     */
    MemoryController(MemoryRepository repository, ConfigurationRepository configurationRepository) {
        this.repository = repository;
        this.configurationRepository = configurationRepository;
    }

    /**
     * Retrieves a list of all Memory objects.
     *
     * @return A list of Memory objects.
     */
    @GetMapping
    List<Memory> all() {
        return repository.findAll();
    }

    /**
     * Creates a new Memory.
     *
     * @param newMemory The Memory object containing the details of the new Memory.
     * @return The saved Memory object.
     */
    @PostMapping
    Memory newMemory(@RequestBody Memory newMemory) {
        return repository.save(newMemory);
    }

    /**
     * Retrieves details of a single Memory by its ID.
     *
     * @param id The ID of the Memory to retrieve.
     * @return The Memory object corresponding to the provided ID.
     * @throws MemoryNotFoundException If no Memory with the given ID is found.
     */
    @GetMapping("/{id}")
    Memory one(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new MemoryNotFoundException(id));
    }

    /**
     * Deletes a Memory by its ID.
     *
     * @param id The ID of the Memory to delete.
     */
    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteMemory(@PathVariable Long id) {
        if (configurationRepository.findAllByMemory(id).size() > 0) {
            return ResponseEntity.badRequest().build();
        };

        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}