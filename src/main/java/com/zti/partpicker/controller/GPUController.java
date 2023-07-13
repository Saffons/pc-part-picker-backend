package com.zti.partpicker.controller;

import java.util.List;

import com.zti.partpicker.exception.GPUNotFoundException;
import com.zti.partpicker.model.GPU;
import com.zti.partpicker.repository.GPURepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/parts/gpu/")
public class GPUController {

    @Autowired
    private final GPURepository repository;

    GPUController(GPURepository repository) {
        this.repository = repository;
    }

    @GetMapping
    List<GPU> all() {
        return repository.findAll();
    }

    @PostMapping
    GPU newGPU(@RequestBody GPU newGPU) {
        return repository.save(newGPU);
    }

    @GetMapping("/{id}")
    GPU one(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new GPUNotFoundException(id));
    }


    //TODO: repair org.springframework.jdbc.BadSqlGrammarException: PreparedStatementCallback; bad SQL grammar [DELETE FROM SPRING_SESSION
    //WHERE EXPIRY_TIME < ?
//    @PutMapping("/employees/{id}")
//    GPU replaceCPU(@RequestBody GPU newGPU, @PathVariable Long id) {
//
//        return repository.findById(id)
//                .map(gpu -> {
//                    gpu.setName(newGPU.getName());
//                    gpu.setRole(newGPU.getRole());
//                    return repository.save(employee);
//                })
//                .orElseGet(() -> {
//                    newEmployee.setId(id);
//                    return repository.save(newEmployee);
//                });
//    }

    @DeleteMapping("/{id}")
    void deleteGPU(@PathVariable Long id) {
        repository.deleteById(id);
    }
}