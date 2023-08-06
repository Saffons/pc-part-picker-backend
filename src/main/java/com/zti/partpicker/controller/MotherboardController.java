package com.zti.partpicker.controller;

import java.util.List;

import com.zti.partpicker.exception.MotherboardNotFoundException;
import com.zti.partpicker.model.Motherboard;
import com.zti.partpicker.repository.MotherboardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/parts/motherboard")
public class MotherboardController {

    @Autowired
    private final MotherboardRepository repository;

    MotherboardController(MotherboardRepository repository) {
        this.repository = repository;
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
    void deleteMotherboard(@PathVariable Long id) {
        repository.deleteById(id);
    }
}