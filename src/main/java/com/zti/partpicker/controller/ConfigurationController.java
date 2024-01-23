package com.zti.partpicker.controller;

import java.util.List;

import com.zti.partpicker.exception.ConfigurationNotFoundException;
import com.zti.partpicker.mapper.ConfigurationResponse;
import com.zti.partpicker.mapper.ConfigurationService;
import com.zti.partpicker.model.Configuration;
import com.zti.partpicker.repository.ConfigurationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * Controller class responsible for handling configuration-related operations and endpoints.
 */

@CrossOrigin("*")
@RestController
@RequestMapping("/api/configuration")
public class ConfigurationController {

    @Autowired
    private final ConfigurationRepository repository;

    @Autowired
    private final ConfigurationService configurationService;

    /**
     * Constructor for the ConfigurationController class.
     *
     * @param repository An instance of ConfigurationRepository for interacting with the configurations' data.
     * @param configurationService An instance of ConfigurationService for preparing JSON responses
     */
    ConfigurationController(ConfigurationRepository repository, ConfigurationService configurationService) {
        this.repository = repository;
        this.configurationService = configurationService;
    }

    /**
     * Retrieves a list of all configurations.
     *
     * @return A list of Configuration objects.
     */
    @GetMapping
    List<ConfigurationResponse> all() {
        return repository
                .findAll()
                .stream()
                .map((configurationService::createConfigurationResponse))
                .toList();
    }

    /**
     * Retrieves a list of all configurations of one user by their id.
     *
     * @param id ID of a user that owns the configurations
     * @return A list of Configuration objects.
     */
    @GetMapping("/user/{id}")
    List<ConfigurationResponse> allById(@PathVariable Long id) {
        return repository
                .findAllByAccount(id)
                .stream()
                .map((configurationService::createConfigurationResponse))
                .toList();
    }

    /**
     * Creates a new configuration.
     *
     * @param newConfiguration The Configuration object containing the details of the new configuration.
     * @return The saved Configuration object.
     */
    @PostMapping
    Configuration newConfiguration(@RequestBody Configuration newConfiguration) {
        return repository.save(newConfiguration);
    }

    /**
     * Retrieves details of a single configuration by its ID.
     *
     * @param id The ID of the configuration to retrieve.
     * @return The Configuration object corresponding to the provided ID.
     * @throws ConfigurationNotFoundException If no configuration with the given ID is found.
     */
    @GetMapping("/{id}")
    Configuration one(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ConfigurationNotFoundException(id));
    }

    /**
     * Deletes a configuration by its ID.
     *
     * @param id The ID of the configuration to delete.
     */
    @DeleteMapping("/{id}")
    void deleteConfiguration(@PathVariable Long id) {
        repository.deleteById(id);
    }
}