package com.zti.partpicker.controller;

import java.util.List;

import com.zti.partpicker.exception.MemoryNotFoundException;
import com.zti.partpicker.model.Memory;
import com.zti.partpicker.repository.MemoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/parts/memory/")
public class MemoryController {

    @Autowired
    private final MemoryRepository repository;

    MemoryController(MemoryRepository repository) {
        this.repository = repository;
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


    //TODO: repair org.springframework.jdbc.BadSqlGrammarException: PreparedStatementCallback; bad SQL grammar [DELETE FROM SPRING_SESSION
    //WHERE EXPIRY_TIME < ?
//    @PutMapping("/employees/{id}")
//    Memory replaceCPU(@RequestBody Memory newMemory, @PathVariable Long id) {
//
//        return repository.findById(id)
//                .map(Memory -> {
//                    Memory.setName(newMemory.getName());
//                    Memory.setRole(newMemory.getRole());
//                    return repository.save(employee);
//                })
//                .orElseGet(() -> {
//                    newEmployee.setId(id);
//                    return repository.save(newEmployee);
//                });
//    }

    @DeleteMapping("/{id}")
    void deleteMemory(@PathVariable Long id) {
        repository.deleteById(id);
    }
}