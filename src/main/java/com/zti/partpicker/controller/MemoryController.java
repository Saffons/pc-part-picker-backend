package com.zti.partpicker.controller;

import java.util.List;

import com.zti.partpicker.exception.MemoryNotFoundException;
import com.zti.partpicker.model.Memory;
import com.zti.partpicker.repository.ConfigurationRepository;
import com.zti.partpicker.repository.MemoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/parts/memory")
public class MemoryController {

    @Autowired
    private final MemoryRepository repository;

    @Autowired
    private final ConfigurationRepository configurationRepository;

    MemoryController(MemoryRepository repository, ConfigurationRepository configurationRepository) {
        this.repository = repository;
        this.configurationRepository = configurationRepository;
    }

    @GetMapping
    List<Memory> all() {
        return repository.findAll();
    }

    @PostMapping
    Memory newMemory(@RequestBody Memory newMemory) {
        return repository.save(newMemory);
    }

    @GetMapping("/{id}")
    Memory one(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new MemoryNotFoundException(id));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteMemory(@PathVariable Long id) {
        if (configurationRepository.findAllByMemory(id).size() > 0) {
            return ResponseEntity.badRequest().build();
        };

        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}