package com.zti.partpicker.controller;

import java.util.List;

import com.zti.partpicker.exception.CPUNotFoundException;
import com.zti.partpicker.exception.StorageNotFoundException;
import com.zti.partpicker.model.Storage;
import com.zti.partpicker.repository.ConfigurationRepository;
import com.zti.partpicker.repository.StorageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller class responsible for handling Storage-related operations and endpoints.
 */
@CrossOrigin("*")
@RestController
@RequestMapping("/api/parts/storage")
public class StorageController {

    @Autowired
    private final StorageRepository repository;

    @Autowired
    private final ConfigurationRepository configurationRepository;

    /**
     * Constructor for the StorageController class.
     *
     * @param repository An instance of StorageRepository for interacting with the Storages data.
     * @param configurationRepository An instance of ConfigurationRepository for preparing JSON responses
     */
    StorageController(StorageRepository repository, ConfigurationRepository configurationRepository) {
        this.repository = repository;
        this.configurationRepository = configurationRepository;
    }

    /**
     * Retrieves a list of all Storage objects.
     *
     * @return A list of Storage objects.
     */
    @GetMapping
    List<Storage> all() {
        return repository.findAll();
    }

    /**
     * Creates a new Storage.
     *
     * @param newStorage The Storage object containing the details of the new Storage.
     * @return The saved Storage object.
     */
    @PostMapping
    Storage newStorage(@RequestBody Storage newStorage) {
        return repository.save(newStorage);
    }

    /**
     * Retrieves details of a single Storage by its ID.
     *
     * @param id The ID of the Storage to retrieve.
     * @return The Storage object corresponding to the provided ID.
     * @throws StorageNotFoundException If no Storage with the given ID is found.
     */
    @GetMapping("/{id}")
    Storage one(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new StorageNotFoundException(id));
    }

    /**
     * Deletes a Storage by its ID.
     *
     * @param id The ID of the Storage to delete.
     */
    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteStorage(@PathVariable Long id) {
        if (configurationRepository.findAllByStorage(id).size() > 0) {
            return ResponseEntity.badRequest().build();
        };
        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}