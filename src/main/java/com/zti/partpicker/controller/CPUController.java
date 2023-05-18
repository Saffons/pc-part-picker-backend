package com.zti.partpicker.controller;

import java.util.List;

import com.zti.partpicker.exception.CPUNotFoundException;
import com.zti.partpicker.model.CPU;
import com.zti.partpicker.repository.CPURepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/parts/")
public class CPUController {

    @Autowired
    private final CPURepository repository;

    CPUController(CPURepository repository) {
        this.repository = repository;
    }

    @GetMapping("/cpu")
    List<CPU> all() {
        return repository.findAll();
    }

    @PostMapping("/cpu")
    CPU newCPU(@RequestBody CPU newCPU) {
        return repository.save(newCPU);
    }

    @GetMapping("/cpu/{id}")
    CPU one(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new CPUNotFoundException(id));
    }


    //TODO: repair org.springframework.jdbc.BadSqlGrammarException: PreparedStatementCallback; bad SQL grammar [DELETE FROM SPRING_SESSION
    //WHERE EXPIRY_TIME < ?
//    @PutMapping("/employees/{id}")
//    CPU replaceCPU(@RequestBody CPU newCPU, @PathVariable Long id) {
//
//        return repository.findById(id)
//                .map(cpu -> {
//                    cpu.setName(newCPU.getName());
//                    cpu.setRole(newCPU.getRole());
//                    return repository.save(employee);
//                })
//                .orElseGet(() -> {
//                    newEmployee.setId(id);
//                    return repository.save(newEmployee);
//                });
//    }

    @DeleteMapping("/cpu/{id}")
    void deleteCPU(@PathVariable Long id) {
        repository.deleteById(id);
    }
}