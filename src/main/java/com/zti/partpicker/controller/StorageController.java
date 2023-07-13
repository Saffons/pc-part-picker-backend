package com.zti.partpicker.controller;

import java.util.List;

import com.zti.partpicker.exception.StorageNotFoundException;
import com.zti.partpicker.model.Storage;
import com.zti.partpicker.repository.StorageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/parts/storage/")
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


    //TODO: repair org.springframework.jdbc.BadSqlGrammarException: PreparedStatementCallback; bad SQL grammar [DELETE FROM SPRING_SESSION
    //WHERE EXPIRY_TIME < ?
//    @PutMapping("/employees/{id}")
//    Storage replaceCPU(@RequestBody Storage newStorage, @PathVariable Long id) {
//
//        return repository.findById(id)
//                .map(Storage -> {
//                    Storage.setName(newStorage.getName());
//                    Storage.setRole(newStorage.getRole());
//                    return repository.save(employee);
//                })
//                .orElseGet(() -> {
//                    newEmployee.setId(id);
//                    return repository.save(newEmployee);
//                });
//    }

    @DeleteMapping("/{id}")
    void deleteStorage(@PathVariable Long id) {
        repository.deleteById(id);
    }
}