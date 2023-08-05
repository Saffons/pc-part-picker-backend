package com.zti.partpicker.controller;

import java.util.List;

import com.zti.partpicker.exception.ConfigurationNotFoundException;
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

    ConfigurationController(ConfigurationRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    List<Configuration> all() {
        return repository.findAll();
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