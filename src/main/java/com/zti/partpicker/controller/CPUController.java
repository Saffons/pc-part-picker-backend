package com.zti.partpicker.controller;

import java.util.List;

import com.zti.partpicker.exception.CPUNotFoundException;
import com.zti.partpicker.model.CPU;
import com.zti.partpicker.repository.CPURepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/parts/cpu")
public class CPUController {

    @Autowired
    private final CPURepository repository;

    CPUController(CPURepository repository) {
        this.repository = repository;
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
    void deleteCPU(@PathVariable Long id) {
        repository.deleteById(id);
    }
}