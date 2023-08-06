package com.zti.partpicker.controller;

import java.util.List;

import com.zti.partpicker.exception.StorageNotFoundException;
import com.zti.partpicker.model.Storage;
import com.zti.partpicker.repository.StorageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/parts/storage")
public class StorageController {

    @Autowired
    private final StorageRepository repository;

    StorageController(StorageRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    List<Storage> all() {
        return repository.findAll();
    }

    @PostMapping
    Storage newStorage(@RequestBody Storage newStorage) {
        return repository.save(newStorage);
    }

    @GetMapping("/{id}")
    Storage one(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new StorageNotFoundException(id));
    }

    @DeleteMapping("/{id}")
    void deleteStorage(@PathVariable Long id) {
        repository.deleteById(id);
    }
}