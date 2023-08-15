package com.zti.partpicker.controller;

import java.util.List;

import com.zti.partpicker.exception.MotherboardNotFoundException;
import com.zti.partpicker.model.Motherboard;
import com.zti.partpicker.repository.ConfigurationRepository;
import com.zti.partpicker.repository.MotherboardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/parts/motherboard")
public class MotherboardController {

    @Autowired
    private final MotherboardRepository repository;

    @Autowired
    private final ConfigurationRepository configurationRepository;

    MotherboardController(MotherboardRepository repository, ConfigurationRepository configurationRepository) {
        this.repository = repository;
        this.configurationRepository = configurationRepository;
    }

    @GetMapping
    List<Motherboard> all() {
        return repository.findAll();
    }

    @PostMapping
    Motherboard newMotherboard(@RequestBody Motherboard newMotherboard) {
        return repository.save(newMotherboard);
    }

    @GetMapping("/{id}")
    Motherboard one(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new MotherboardNotFoundException(id));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteMotherboard(@PathVariable Long id) {
        if (configurationRepository.findAllByMotherboard(id).size() > 0) {
            return ResponseEntity.badRequest().build();
        };
        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}