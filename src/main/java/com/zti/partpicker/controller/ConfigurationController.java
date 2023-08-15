package com.zti.partpicker.controller;

import java.util.List;

import com.zti.partpicker.exception.ConfigurationNotFoundException;
import com.zti.partpicker.mapper.ConfigurationResponse;
import com.zti.partpicker.mapper.ConfigurationService;
import com.zti.partpicker.model.Configuration;
import com.zti.partpicker.repository.ConfigurationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/configuration")
public class ConfigurationController {

    @Autowired
    private final ConfigurationRepository repository;

    @Autowired
    private final ConfigurationService configurationService;

    ConfigurationController(ConfigurationRepository repository, ConfigurationService configurationService) {
        this.repository = repository;
        this.configurationService = configurationService;
    }

    @GetMapping
    List<ConfigurationResponse> all() {
        return repository
                .findAll()
                .stream()
                .map((configurationService::createConfigurationResponse))
                .toList();
    }

    @GetMapping("/user/{id}")
    List<ConfigurationResponse> allById(@PathVariable Long id) {
        return repository
                .findAllByAccount(id)
                .stream()
                .map((configurationService::createConfigurationResponse))
                .toList();
    }

    @PostMapping
    Configuration newConfiguration(@RequestBody Configuration newConfiguration) {
        return repository.save(newConfiguration);
    }

    @GetMapping("/{id}")
    Configuration one(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ConfigurationNotFoundException(id));
    }

    @DeleteMapping("/{id}")
    void deleteConfiguration(@PathVariable Long id) {
        repository.deleteById(id);
    }
}