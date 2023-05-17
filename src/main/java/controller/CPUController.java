package controller;

import java.util.List;

import exception.CPUNotFoundException;
import model.CPU;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import repository.CPURepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/parts")
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
    CPU newCPU(@RequestBody CPU newEmployee) {
        return repository.save(newEmployee);
    }

    @GetMapping("/cpu/{id}")
    CPU one(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new CPUNotFoundException(id));
    }

//    @PutMapping("/employees/{id}")
//    CPU replaceEmployee(@RequestBody CPU newEmployee, @PathVariable Long id) {
//
//        return repository.findById(id)
//                .map(employee -> {
//                    employee.setName(newEmployee.getName());
//                    employee.setRole(newEmployee.getRole());
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