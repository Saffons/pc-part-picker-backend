package com.zti.partpicker.controller;

import com.zti.partpicker.exception.CPUNotFoundException;
import com.zti.partpicker.model.CPU;
import com.zti.partpicker.repository.CPURepository;
import com.zti.partpicker.repository.ConfigurationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/parts/cpu")
public class CPUController {

    @Autowired
    private final CPURepository repository;

    @Autowired
    private final ConfigurationRepository configurationRepository;

    CPUController(CPURepository repository, ConfigurationRepository configurationRepository) {
        this.repository = repository;
        this.configurationRepository = configurationRepository;
    }

    @GetMapping
    List<CPU> all() {
        return repository.findAll();
    }

    @PostMapping
    CPU newCPU(@RequestBody CPU newCPU) {
        return repository.save(newCPU);
    }

    @GetMapping("/{id}")
    CPU one(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new CPUNotFoundException(id));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteCPU(@PathVariable Long id) {
        if (configurationRepository.findAllByCpu(id).size() > 0) {
            return ResponseEntity.badRequest().build();
        };

        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}